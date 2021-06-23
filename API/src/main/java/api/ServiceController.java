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

import api.data.EmployeeRepositoty;
import api.data.ServiceRepository;
import api.model.Employee;
import api.model.Service;

@RestController
@RequestMapping(path = "/service" , produces = "application/json")
@CrossOrigin(origins = "*")
public class ServiceController {
	private ServiceRepository serviceRepository ; 
	@Autowired
	public ServiceController(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository ;
	}
	@GetMapping("/recent")
	public Iterable<Service> getAllService(){
		return serviceRepository.findAll(); 
	}
	@PutMapping("/update/{id}")
	public Service updateService(@PathVariable("id")String id,@RequestBody Service service) {
		 return  serviceRepository.save(service); 
	}
	@GetMapping("/{id}")
	public Service getServiceById(@PathVariable("id") String id) {
		Optional<Service> service = serviceRepository.findById(id);
		if (service.isPresent()) {
			return service.get();
		}
		return null;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteService(@PathVariable("id")String id) {
		serviceRepository.deleteById(id);
	}
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Service postService(@RequestBody Service service) {
		return serviceRepository.save(service);
	}
}
