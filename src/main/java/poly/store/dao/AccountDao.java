package poly.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import poly.store.entity.Account;


@Repository
public interface AccountDao extends JpaRepository<Account, String> {
	Optional<Account> findByUsername(String username);
	
	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('DIRE', 'STAF')")
	List<Account> getAccounts();
}
