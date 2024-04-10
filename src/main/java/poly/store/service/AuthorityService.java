package poly.store.service;

import java.util.List;

import poly.store.entity.Authority;

public interface AuthorityService {

	List<Authority> findAll();

	List<Authority> getAuthoritiesOfAdministrators();

	Authority create(Authority auth);

	void delete(Integer id);

}
