package poly.store.service;

import java.util.List;

import poly.store.entity.Role;

public interface RoleService {

	List<Role> getAll();
	
	Role findById(String id);

}
