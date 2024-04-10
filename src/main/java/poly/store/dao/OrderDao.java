package poly.store.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poly.store.entity.Account;
import poly.store.entity.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
	Page<Order> findByAccountOrderByCreateDateDesc(Account account, Pageable pageable);
}
