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


import api.data.StaffRepository;
import api.model.Staff;
@RestController
@RequestMapping(path = "/staffs" , produces = "application/json")
@CrossOrigin(origins = "*")
public class StaffController {
	private StaffRepository staffRepository ; 
	@Autowired
	public StaffController(StaffRepository staffRepository) {
		this.staffRepository = staffRepository ;
	}
	@GetMapping("/recent")
	public Iterable<Staff> getAllStaff(){
		return staffRepository.findAll(); 
	}
	@PutMapping("/update/{id}")
	public Staff updateStaff(@PathVariable("id")String id,@RequestBody Staff staff) {
		 return  staffRepository.save(staff); 
	}
	@GetMapping("/{id}")
	public Staff getStaffById(@PathVariable("id") String id) {
		Optional<Staff> staff = staffRepository.findById(id);
		if (staff.isPresent()) {
			return staff.get();
		}
		return null;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteStaff(@PathVariable("id")String id) {
		staffRepository.deleteById(id);
	}
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Staff postStaff(@RequestBody Staff staff) {
		return staffRepository.save(staff);
	}
}
