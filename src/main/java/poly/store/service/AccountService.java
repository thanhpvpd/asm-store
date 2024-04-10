package poly.store.service;

import java.util.List;
import java.util.Optional;

import poly.store.entity.Account;

public interface AccountService {
	Optional<Account> findByUsername(String username);

	List<Account> getAdministrators();

	List<Account> findAll();
	
	Account create(Account acc);
}
