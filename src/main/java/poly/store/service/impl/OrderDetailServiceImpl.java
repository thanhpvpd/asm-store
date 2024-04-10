package poly.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.DetailDao;
import poly.store.entity.OrderDetail;
import poly.store.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	DetailDao dao;

	@Override
	public OrderDetail findById(Long id) {
		return dao.getReferenceById(id);
	}
}
