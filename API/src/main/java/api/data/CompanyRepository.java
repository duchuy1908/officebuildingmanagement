package api.data;

import org.springframework.data.repository.CrudRepository;

import api.model.Company;

public interface CompanyRepository extends CrudRepository<Company, String> {
	
}
