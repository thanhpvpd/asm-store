package poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.AccountDao;
import poly.store.dao.AuthorityDao;
import poly.store.entity.Account;
import poly.store.entity.Authority;
import poly.store.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	AuthorityDao dao;
	@Autowired
	AccountDao adao;
	
	@Override
	public List<Authority> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Authority> getAuthoritiesOfAdministrators() {
		List<Account> accounts = adao.getAccounts();
		return dao.authoritiesOf(accounts);
	}

	@Override
	public Authority create(Authority auth) {
		return dao.save(auth);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}

}
