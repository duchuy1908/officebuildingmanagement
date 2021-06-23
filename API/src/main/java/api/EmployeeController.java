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
import api.data.StaffRepository;
import api.model.Employee;
import api.model.Staff;

@RestController
@RequestMapping(path = "/employees" , produces = "application/json")
@CrossOrigin(origins = "*")
public class EmployeeController {
	private EmployeeRepositoty employeeRepositoty ; 
	@Autowired
	public EmployeeController(EmployeeRepositoty employeeRepositoty) {
		this.employeeRepositoty = employeeRepositoty ;
	}
	@GetMapping("/recent")
	public Iterable<Employee> getAllNvtn(){
		return employeeRepositoty.findAll(); 
	}
	@PutMapping("/update/{id}")
	public Employee updateEmployee(@PathVariable("id")String id,@RequestBody Employee employee) {
		 return  employeeRepositoty.save(employee); 
	}
	@GetMapping("/{id}")
	public Employee employeeById(@PathVariable("id") String id) {
		Optional<Employee> employee = employeeRepositoty.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		}
		return null;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable("id")String id) {
		employeeRepositoty.deleteById(id);
	}
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee postTaco(@RequestBody Employee employee) {
		return employeeRepositoty.save(employee);
	}
}
