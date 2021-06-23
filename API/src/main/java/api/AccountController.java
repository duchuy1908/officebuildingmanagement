package api;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import api.data.AccountRepository;
import api.model.Account;
import api.model.Staff;

@RestController
@RequestMapping(path = "/accounts" , produces = "application/json")
@CrossOrigin(origins = "*")
public class AccountController {
	private AccountRepository accountRepository ; 
	@Autowired
	public AccountController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository ; 
	}
	@GetMapping("/recent")
	public Iterable<Account> getAllAccount() {
		return accountRepository.findAll();
	}
	@PutMapping("/update/{id}")
	public Account updateAccount(@PathVariable("id")String id,@RequestBody Account account) {
		 return  accountRepository.save(account); 
	}
	@GetMapping("/{id}")
	public Account getStaffById(@PathVariable("id")String id) {
		Optional<Account> account = accountRepository.findById(id);
		if (account.isPresent()) {
			return account.get();
		}
		return null;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteAccount(@PathVariable("id")String id) {
		accountRepository.deleteById(id);
	}
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Account postAccount(@RequestBody Account account) {
		return accountRepository.save(account);
	}
}
