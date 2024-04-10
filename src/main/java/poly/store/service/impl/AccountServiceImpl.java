package poly.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.AccountDao;
import poly.store.entity.Account;
import poly.store.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountDao dao;

	@Override
	public Optional<Account> findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public List<Account> getAdministrators() {
		
		return dao.getAccounts();
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Account create(Account acc) {
		return dao.save(acc);
	}

}
