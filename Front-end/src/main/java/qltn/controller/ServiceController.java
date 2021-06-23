package qltn.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import qltn.model.Company_service;
import qltn.model.Employee;
import qltn.model.Service;
@Controller
@RequestMapping("/service")
public class ServiceController {
	private RestTemplate rest = new RestTemplate() ;  
	@GetMapping
	public String show(Model model) {
		List<Service> services = Arrays.asList(rest.getForObject("http://localhost:8080/service/recent",Service[].class));
		model.addAttribute("service",services); 
		return "ServiceManagement" ; 
	}
	@GetMapping("/delete/{id}")
	public String deleteService(@PathVariable String id,Model model) {
		int id1=0; 
		List<Company_service> company_services = Arrays.asList(rest.getForObject("http://localhost:8080/company_services/recent",Company_service[].class));
		Service service = rest.getForObject("http://localhost:8080/service/"+id, Service.class);
		for (Company_service company_service  : company_services) {
			if(company_service.getService().getMadv().equals(id)) {
				id1 = company_service.getIdRegister();
			}
			
		}
		if(id1!=0) {
			rest.delete("http://localhost:8080/company_services/delete/"+id1);
			rest.delete("http://localhost:8080/service/delete/"+id);
			
		}
		else rest.delete("http://localhost:8080/service/delete/"+id);
		return "redirect:/service" ; 
	}
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id")String id,Model model) {
		Service service = rest.getForObject("http://localhost:8080/service/"+id, Service.class);
			model.addAttribute("service",service);
		return "editServiceForm" ; 
	}
	@GetMapping("/search")
	public String searchService(Model model,@RequestParam("id")String ten) {
		List<Service> services= Arrays.asList(rest.getForObject("http://localhost:8080/service/recent",Service[].class));
		List<Service> list = new ArrayList<>();
		for(Service service : services) {
			if(service.getTendv().toLowerCase().contains(ten)){
				list.add(service);
			}
		}
		model.addAttribute("service",list); 
		return "ServiceManagement" ;
	}
	@PostMapping("/edit/{id}")
	public String updateService(@PathVariable("id")String id,@RequestParam("tendv")String tendv,@RequestParam("loaidv")String loaidv,@RequestParam("dongia")Float dongia) {
		Service service = rest.getForObject("http://localhost:8080/service/"+id, Service.class);
		service.setTendv(tendv);
		service.setLoaidv(loaidv);
		service.setDongia(dongia);
		rest.put("http://localhost:8080/service/update/"+id,service);
		return "redirect:/service";
	}
	@PostMapping("/new")
	public  String addService(Model model,@RequestParam("madv")String madv,@RequestParam("tendv")String tendv,@RequestParam("loaidv")String loaidv,@RequestParam("dongia")Float dongia){
		Service service = new Service();
		service.setMadv(madv);
		service.setTendv(tendv);
		service.setLoaidv(loaidv);
		service.setDongia(dongia);
		rest.postForObject("http://localhost:8080/service", service, Service.class);
		return "redirect:/service";
	}
}
