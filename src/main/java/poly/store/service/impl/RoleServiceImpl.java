package poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.RoleDao;
import poly.store.entity.Role;
import poly.store.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleDao dao;
	
	@Override
	public List<Role> getAll() {
		return dao.findAll();
	}

	@Override
	public Role findById(String id) {
		return dao.findById(id).get();
	}

}
