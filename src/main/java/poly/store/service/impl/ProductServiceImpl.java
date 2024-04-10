package poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import poly.store.dao.CategoryDao;
import poly.store.dao.ProductDao;
import poly.store.entity.Category;
import poly.store.entity.Product;
import poly.store.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao dao;
    @Autowired
    CategoryDao daoCategory;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public Product findById(Integer id) {
        return dao.findById(id).get();
    }

	@Override
	public Page<Product> findByCategory(String id, Pageable pageable) {
		Category category = daoCategory.findById(id).get();
		Page<Product> list = dao.findByCategory(category, pageable);
		return list;
	}

	@Override
	public List<Product> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		return dao.save(product);
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return dao.save(product);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public Page<Product> findName(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findName(name, pageable);
	}

}
