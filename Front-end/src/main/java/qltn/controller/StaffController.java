package qltn.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import qltn.model.Staff;

@Controller
@RequestMapping("/staff")
public class StaffController {
	private RestTemplate rest = new RestTemplate() ;  
	@GetMapping
	public String show(Model model) {
		List<Staff> nvtnList = Arrays.asList(rest.getForObject("http://localhost:8080/staffs/recent",Staff[].class));
		model.addAttribute("nvtn",nvtnList); 
		return "StaffManagement" ; 
	}
	@GetMapping("/delete/{id}")
	public String deleteStaff(@PathVariable String id,Model model) {
		//List<Staff> nvtnList = Arrays.asList(rest.getForObject("http://localhost:8080/employees/"+id,Staff[].class));
		rest.delete("http://localhost:8080/staffs/delete/"+id);
		return "redirect:/staff" ; 
	}
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id")String id,Model model) {
		Staff staff  = rest.getForObject("http://localhost:8080/staffs/"+id, Staff.class);
			model.addAttribute("staff",staff);
		return "editStaffForm" ; 
	}
	@GetMapping("/search")
	public String searchStaff(Model model,@RequestParam("id")String ten) {
		List<Staff> staffs= Arrays.asList(rest.getForObject("http://localhost:8080/staffs/recent",Staff[].class));
		List<Staff> list = new ArrayList<>();
		for(Staff staff : staffs) {
			if(staff.getHoten().toLowerCase().contains(ten)){
				list.add(staff);
			}
			if(staff.getManv().toLowerCase().contains(ten)){
					list.add(staff);
			}
			if(staff.getDiachi().toLowerCase().contains(ten)){
				list.add(staff);
			}
			if(staff.getSdt().toLowerCase().contains(ten)){
				list.add(staff);
			}
			if(staff.getVitri().toLowerCase().contains(ten)){
				list.add(staff);
			}
			if(staff.getBac().toLowerCase().contains(ten)){
				list.add(staff);
			}
		}
		model.addAttribute("nvtn",list); 
		return "StaffManagement" ;
	}
	@PostMapping("/edit/{id}")
	public String updateStaff(@PathVariable("id")String id,@RequestParam("hoten")String hoten,@RequestParam("ngaysinh")String ngaysinh,@RequestParam("diachi")String diachi,@RequestParam("sdt")String sdt,@RequestParam("bac")String bac,@RequestParam("vitri")String vitri) {
		Staff nvtNha  = rest.getForObject("http://localhost:8080/staffs/"+id, Staff.class);
		nvtNha.setHoten(hoten);
		Date ns = Date.valueOf(ngaysinh);
		nvtNha.setNgaysinh(ns);
		nvtNha.setDiachi(diachi);
		nvtNha.setSdt(sdt);
		nvtNha.setBac(bac);
		nvtNha.setVitri(vitri);
		rest.put("http://localhost:8080/staffs/update/"+id,nvtNha);
		return "redirect:/staff";
	}
	@PostMapping("/new")
	public  String addStaff(Model model,@RequestParam("manv")String manv,@RequestParam("hoten")String hoten,@RequestParam("ngaysinh")String ngaysinh,@RequestParam("diachi")String diachi,@RequestParam("sdt")String sdt,@RequestParam("bac")String bac,@RequestParam("vitri")String vitri){
		Staff nvtNha= new Staff();
		nvtNha.setManv(manv);
		nvtNha.setHoten(hoten);
		Date ns = Date.valueOf(ngaysinh);
		nvtNha.setNgaysinh(ns);
		nvtNha.setDiachi(diachi);
		nvtNha.setSdt(sdt);
		nvtNha.setBac(bac);
		nvtNha.setVitri(vitri);
		model.addAttribute("nvtn",nvtNha);
		rest.postForObject("http://localhost:8080/staffs", nvtNha, Staff.class);
		return "redirect:/staff";
	}
}
