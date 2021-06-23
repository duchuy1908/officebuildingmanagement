package qltn.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.expression.Lists;

import qltn.model.Account;
import qltn.model.Company;
import qltn.model.Company_service;
import qltn.model.Employee;
import qltn.model.Service;
@Controller
@RequestMapping("/company")
public class CompanyController {
	private RestTemplate rest = new RestTemplate() ;  
	@GetMapping
	public String show(Model model) {
		List<Company> companys = Arrays.asList(rest.getForObject("http://localhost:8080/companys/recent",Company[].class));
		model.addAttribute("company",companys); 
		return "CompanyManagement" ; 
	}
	@GetMapping("/register_service")
	public String register(Model model) {
		List<Service> services = Arrays.asList(rest.getForObject("http://localhost:8080/service/recent",Service[].class));
		model.addAttribute("service",services); 
		return "RegisterService" ; 
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id,Model model) {
		int id1=0; 
		List<Company_service> company_services = Arrays.asList(rest.getForObject("http://localhost:8080/company_services/recent",Company_service[].class));
		Company company = rest.getForObject("http://localhost:8080/companys/"+id, Company.class);
		for (Company_service company_service  : company_services) {
			if(company_service.getCompany().getMacty().equals(id)) {
				id1 = company_service.getIdRegister();
			}
		}
		if(id1!=0) {
			rest.delete("http://localhost:8080/company_services/delete/"+id1);
			rest.delete("http://localhost:8080/companys/delete/"+id);
		}
		else rest.delete("http://localhost:8080/companys/delete/"+id);
		return "redirect:/company" ; 
	}
	@GetMapping("/signed/{id}")
	public String sign(Model model , @PathVariable("id")String id,HttpSession ss) {
		Account account =   (Account) ss.getAttribute("account");
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		Service service = rest.getForObject("http://localhost:8080/service/"+id, Service.class);
		//List<Company_service> company_services =  Arrays.asList(rest.getForObject("http://localhost:8080/company_services/recent", Company_service.class));
		Company company = rest.getForObject("http://localhost:8080/companys/" + account.getUsername(), Company.class); 
		/*for (Company_service company_service : company_services) {
			if(company_service.getCompany().getMacty().equals(account.getUsername())&&company_service.getService().getMadv().equals(service.getMadv())) {
				return "login";
			}
		}*/
		Company_service company_service = new Company_service();
		company_service.setCompany(company);
		company_service.setService(service);
		company_service.setRegisterTime(date);
		rest.postForObject("http://localhost:8080/company_services", company_service, Company_service.class);
		return "redirect:/company/register_service";
	}
	@GetMapping("/search")
	public String searchCompany(Model model,@RequestParam("id")String ten) {
		List<Company> companys= Arrays.asList(rest.getForObject("http://localhost:8080/companys/recent",Company[].class));
		List<Company> list = new ArrayList<>();
		for(Company company : companys) {
			if(company.getTen().toLowerCase().contains(ten) ){
				list.add(company);
			}
		}
		model.addAttribute("company",list); 
		return "CompanyManagement" ;
	}
	/*@GetMapping("/delete/{id}")
	public String deleteCompany(@PathVariable String id,Model model) {
		rest.delete("http://localhost:8080/companys/delete/"+id);
		return "redirect:/company" ; 
	}*/
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id")String id,Model model) {
		Company company = rest.getForObject("http://localhost:8080/companys/"+id, Company.class);
			model.addAttribute("company",company);
		return "editCompanyForm" ; 
	}
	@PostMapping("/edit/{id}")
	public String updateCompany(@PathVariable("id")String id,@RequestParam("ten")String ten,
			@RequestParam("msthue")String msthue,@RequestParam("vondieule")float vondieule,
			@RequestParam("linhvuc")String linhvuc,@RequestParam("sonv")int sonv,@RequestParam("diachi")String diachi,@RequestParam("sdt")String sdt,@RequestParam("dientich")float dientich) {
		Company company = rest.getForObject("http://localhost:8080/companys/"+id, Company.class);
		company.setTen(ten);
		company.setMsthue(msthue);
		company.setVondieule(vondieule);
		company.setSonv(sonv);
		company.setLinhvuc(linhvuc);
		company.setSdt(sdt);
		company.setDientich(dientich);
		company.setDiachi(diachi);
		rest.put("http://localhost:8080/companys/update/"+id,company);
		return "redirect:/company";
	}
	@PostMapping("/new")
	public  String addCompany(Model model,@RequestParam("macty")String macty,@RequestParam("ten")String ten,
			@RequestParam("msthue")String msthue,@RequestParam("vondieule")float vondieule,
			@RequestParam("linhvuc")String linhvuc,@RequestParam("sonv")int sonv,@RequestParam("diachi")String diachi,@RequestParam("sdt")String sdt,@RequestParam("dientich")float dientich){
		Company company = new Company();
		company.setMacty(macty);
		company.setTen(ten);
		company.setMsthue(msthue);
		company.setVondieule(vondieule);
		company.setSonv(sonv);
		company.setLinhvuc(linhvuc);
		company.setSdt(sdt);
		company.setDientich(dientich);
		company.setDiachi(diachi);
		rest.postForObject("http://localhost:8080/companys", company, Company.class);
		return "redirect:/company";
	}
}
