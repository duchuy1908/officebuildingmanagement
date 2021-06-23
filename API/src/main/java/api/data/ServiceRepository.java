package api.data;

import org.springframework.data.repository.CrudRepository;

import api.model.Service;

public interface ServiceRepository extends CrudRepository<Service, String> {

}
