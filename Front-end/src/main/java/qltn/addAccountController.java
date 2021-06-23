package qltn;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import qltn.model.Account;

@Controller
@RequestMapping("/new")
public class addAccountController {
		private RestTemplate rest ; 
		@GetMapping
		public String showForm() {
			return "addAccountForm" ; 
		}
      @PostMapping
       public String newAcc(Model model , @RequestParam(name = "username1")String username1,@RequestParam(name = "password1")String password1 , @RequestParam(name = "password2")String password2) {	
    	 if(password1.equals(password2)) {
    		  Account account = new Account(); 
    		  account.setUsername(username1);
    		  account.setPassword(password1);
    		  rest.postForObject("http://localhost:8080/new", account, Account[].class); 		 
    	  }
    	  return "redirect:/"; 
      }
}
