package qltn.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import qltn.model.Company_service;
import qltn.model.Employee;
import qltn.model.Staff;

@Controller
@RequestMapping("/company_service")
public class Company_serviceController {
	private RestTemplate rest = new RestTemplate() ;  
	@GetMapping
	public String show(Model model) {
		List<Company_service> company_services = Arrays.asList(rest.getForObject("http://localhost:8080/company_services/recent",Company_service[].class));
		model.addAttribute("company_service",company_services); 
		return "Company_serviceForm" ; 
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id,Model model) {
		rest.delete("http://localhost:8080/company_services/delete/"+id);
		return "redirect:/company_service";
	}
	@GetMapping("/search")
	public String searchStaff(Model model,@RequestParam("id")String ten) {
		List<Company_service> company_services= Arrays.asList(rest.getForObject("http://localhost:8080/company_services/recent",Company_service[].class));
		List<Company_service> list = new ArrayList<>();
		for(Company_service company_service : company_services) {
			if(company_service.getCompany().getTen().toLowerCase().contains(ten)){
				list.add(company_service);
			}
			if(company_service.getCompany().getMacty().toLowerCase().contains(ten)){
				list.add(company_service);
			}
			
			if(company_service.getService().getTendv().toLowerCase().contains(ten)){
				list.add(company_service);
			}
		}
		model.addAttribute("company_service",list); 
		return "Company_serviceForm" ;
	}
}
