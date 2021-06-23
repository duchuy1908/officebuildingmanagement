package api.data;

import org.springframework.data.repository.CrudRepository;

import api.model.Staff;

public interface StaffRepository extends CrudRepository<Staff,String> {
	
}
