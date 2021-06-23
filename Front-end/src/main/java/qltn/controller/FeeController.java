package qltn.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import qltn.model.Employee;
import qltn.model.Fee;

@Controller
@RequestMapping("/fee")
public class FeeController {
	private RestTemplate rest = new RestTemplate() ;  
	@GetMapping
	public String show(Model model) {
		List<Fee> fees = Arrays.asList(rest.getForObject("http://localhost:8080/fees/recent",Fee[].class));
		model.addAttribute("fee",fees); 
		return "FeeManagement" ; 
	}
}
