package api.data;

import org.springframework.data.repository.CrudRepository;

import api.model.Account;

public interface AccountRepository extends CrudRepository<Account, String> {

}
