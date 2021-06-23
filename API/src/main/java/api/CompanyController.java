package api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
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

import api.data.CompanyRepository;
import api.data.StaffRepository;
import api.model.Company;
import api.model.Staff;

@RestController
@RequestMapping(path = "/companys" , produces = "application/json")
@CrossOrigin(origins = "*")
public class CompanyController {
	private CompanyRepository companyRepository ; 
	@Autowired
	public CompanyController(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository ;
	}
	@GetMapping("/recent")
	public Iterable<Company> getAllCompany(){
		return companyRepository.findAll(); 
	}
	@PutMapping("/update/{id}")
	public Company updateCompany(@PathVariable("id")String id,@RequestBody Company company) {
		 return  companyRepository.save(company); 
	}
	@GetMapping("/{id}")
	public Company getCompanyById(@PathVariable("id") String id) {
		Optional<Company> company = companyRepository.findById(id);
		if (company.isPresent()) {
			return company.get();
		}
		return null;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCompany(@PathVariable("id")String id) {
		companyRepository.deleteById(id);
	}
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Company postCompany(@RequestBody Company company) {
		return companyRepository.save(company);
	}
}
