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

import qltn.model.Account;

@Controller
@RequestMapping("/account")
public class AccountController {
	private RestTemplate rest = new RestTemplate() ;  
	@GetMapping
	public String show(Model model) {
		List<Account> accounts= Arrays.asList(rest.getForObject("http://localhost:8080/accounts/recent",Account[].class));
		model.addAttribute("account",accounts); 
		return "AccountManagement" ; 
	}
	@GetMapping("/search")
	public String searchAccount(Model model,@RequestParam("id")String username) {
		List<Account> accounts= Arrays.asList(rest.getForObject("http://localhost:8080/accounts/recent",Account[].class));
		List<Account> list = new ArrayList();
		for(Account account : accounts) {
			if(account.getUsername().toLowerCase().contains(username)) {
				list.add(account);
			}
		}
		model.addAttribute("account",list); 
		return "AccountManagement" ;
	}
	@GetMapping("/delete/{id}")
	public String deleteAccount(@PathVariable String id,Model model) {
		rest.delete("http://localhost:8080/accounts/delete/"+id);
		return "redirect:/account" ; 
	}
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id")String id,Model model) {
		Account account = rest.getForObject("http://localhost:8080/accounts/"+id, Account.class);
			model.addAttribute("employee",account);
		return "editAccountForm" ; 
	}
	@PostMapping("/edit/{id}")
	public String updateAccount(@PathVariable("id")String id,@RequestParam("username")String username,@RequestParam("password")String password) {
		Account account = rest.getForObject("http://localhost:8080/accounts/"+id, Account.class);
		account.setUsername(username);
		account.setPassword(password);
		rest.put("http://localhost:8080/accounts/update/"+id,account);
		return "redirect:/account";
	}
	@PostMapping("/new")
	public  String addAccount(Model model,@RequestParam("username")String username,@RequestParam("password")String password){
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		rest.postForObject("http://localhost:8080/accounts", account, Account.class);
		return "redirect:/account";
	}
}
