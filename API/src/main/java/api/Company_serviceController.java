package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.data.Company_serviceRepository;
import api.data.EmployeeRepositoty;
import api.model.Company;
import api.model.Company_service;
import api.model.Employee;

@RestController
@RequestMapping(path = "/company_services" , produces = "application/json")
@CrossOrigin(origins = "*")
public class Company_serviceController {
	private Company_serviceRepository company_serviceRepository; 
	@Autowired
	public Company_serviceController(Company_serviceRepository company_serviceRepository) {
		this.company_serviceRepository = company_serviceRepository ;
	}
	@GetMapping("/recent")
	public Iterable<Company_service> getAllCompany_service(){
		return company_serviceRepository.findAll(); 
	}
	@DeleteMapping("/delete/{id}")
	public void deleteCompany_service(@PathVariable("id")int id) {
		company_serviceRepository.deleteById(id);
	}
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Company_service postCompany_service(@RequestBody Company_service company_service) {
		return company_serviceRepository.save(company_service);
	}
}
