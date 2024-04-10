package poly.store.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import poly.store.dao.DetailDao;
import poly.store.dao.OrderDao;
import poly.store.entity.Account;
import poly.store.entity.Order;
import poly.store.entity.OrderDetail;
import poly.store.service.AccountService;
import poly.store.service.OrderSevice;

@Service
public class OrderSeviceImpl implements OrderSevice{
	@Autowired
	OrderDao dao;
	@Autowired
	DetailDao ddao;
	@Autowired
	AccountService accountService;

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
		};
		List<OrderDetail> list = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		ddao.saveAll(list);
		
		return order;
	}

	@Override
	public Order findById(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id).get();
	}

	@Override
	public Page<Order> findByAccount(String username, Pageable pageable) {
		Account acc = accountService.findByUsername(username).get();
		return dao.findByAccountOrderByCreateDateDesc(acc, pageable);
	}
	
}
