package api.data;

import org.springframework.data.repository.CrudRepository;

import api.model.Employee;

public interface EmployeeRepositoty extends CrudRepository<Employee, String> {
	
}
