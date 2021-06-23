package api.data;

import org.springframework.data.repository.CrudRepository;

import api.model.Fee;

public interface FeeRepository  extends CrudRepository<Fee, Integer>{
	
}
