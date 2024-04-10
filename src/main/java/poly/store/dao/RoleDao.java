package poly.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poly.store.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {

}
