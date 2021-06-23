package qltn.controller;

import java.util.Arrays;
import java.util.List;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.unbescape.css.CssIdentifierEscapeLevel;

import com.fasterxml.jackson.databind.type.ResolvedRecursiveType;

import qltn.model.Account;
import qltn.model.Service;
@Controller
@RequestMapping("/home")
public class loginController {
	private RestTemplate rest = new RestTemplate(); 
	@PostMapping
    public String checkLogin(Model model,HttpSession ss,@RequestParam(name = "username")String username , @RequestParam(name ="password")String password ) { 
		if(username.equalsIgnoreCase("admin")) {
			List<Account> accounts =  Arrays.asList(rest.getForObject("http://localhost:8080/accounts/recent",Account[].class));
			for(Account account : accounts) {
				if(account.getPassword().equals(password)) {
					return "ManagementHome";
				}
			}	
		} 
		else if (username.contains("CT")) {
			Account account = rest.getForObject("http://localhost:8080/accounts/"+username,Account.class);
			if(account.getUsername().equals(username) && account.getPassword().equals(password)) {
				List<Service> services = Arrays.asList(rest.getForObject("http://localhost:8080/service/recent",Service[].class));
				model.addAttribute("service",services); 
				ss.setAttribute("account", account);
				return "RegisterService" ; 
			}
		}
		return "login" ; 
	}
    	      
}
