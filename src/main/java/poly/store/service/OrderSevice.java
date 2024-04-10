package poly.store.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.databind.JsonNode;

import poly.store.entity.Order;

public interface OrderSevice {

	Order create(JsonNode orderData);

	Order findById(Long id);

	Page<Order> findByAccount(String username, Pageable pageable);

}
