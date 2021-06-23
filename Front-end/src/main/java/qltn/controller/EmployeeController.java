package qltn.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import qltn.model.Employee;
import qltn.model.Staff;
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	private RestTemplate rest = new RestTemplate() ;  
	@GetMapping
	public String show(Model model) {
		List<Employee> employees = Arrays.asList(rest.getForObject("http://localhost:8080/employees/recent",Employee[].class));
		model.addAttribute("employee",employees); 
		return "EmployeeManagement" ; 
	}
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable String id,Model model) {
		rest.delete("http://localhost:8080/employees/delete/"+id);
		return "redirect:/employee" ; 
	}
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id")String id,Model model) {
		Employee employee = rest.getForObject("http://localhost:8080/employees/"+id, Employee.class);
			model.addAttribute("employee",employee);
		return "editEmployeeForm" ; 
	}
	@GetMapping("/search")
	public String searchEmployee(Model model,@RequestParam("id")String ten) {
		List<Employee> employees= Arrays.asList(rest.getForObject("http://localhost:8080/employees/recent",Employee[].class));
		List<Employee> list = new ArrayList<>();
		for(Employee employee : employees) {
			if(employee.getTen().toLowerCase().contains(ten)){
				list.add(employee);
			}
			if(employee.getCmt().toLowerCase().contains(ten)){
				list.add(employee);
			}
			if(employee.getManv().toLowerCase().contains(ten)){
				list.add(employee);
			}
			if(employee.getSdt().toLowerCase().contains(ten)){
				list.add(employee);
			}
		}
		model.addAttribute("employee",list); 
		return "EmployeeManagement" ;
	}
	@PostMapping("/edit/{id}")
	public String updateEmployee(@PathVariable("id")String id,@RequestParam("cmt")String cmt,@RequestParam("ten")String ten,@RequestParam("ngaysinh")String ngaysinh,@RequestParam("sdt")String sdt) {
		Employee employee = rest.getForObject("http://localhost:8080/employees/"+id, Employee.class);
		employee.setCmt(cmt);
		Date ns = Date.valueOf(ngaysinh);	
		employee.setNgaysinh(ns);		
		employee.setTen(ten);
		employee.setSdt(sdt);
		rest.put("http://localhost:8080/employees/update/"+id,employee);
		return "redirect:/employee";
	}
	@PostMapping("/new")
	public  String addEmployee(Model model,@RequestParam("manv")String manv,@RequestParam("cmt")String cmt,@RequestParam("ten")String ten,@RequestParam("ngaysinh")String ngaysinh,@RequestParam("sdt")String sdt){
		Employee employee = new Employee();
		employee.setManv(manv);
		employee.setCmt(cmt);
		employee.setTen(ten);
		Date ns = Date.valueOf(ngaysinh);	
		employee.setNgaysinh(ns);		
		employee.setSdt(sdt);
		rest.postForObject("http://localhost:8080/employees", employee, Employee.class);
		return "redirect:/employee";
	}
}
