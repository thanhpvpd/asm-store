package poly.store.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import poly.store.entity.Product;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Product findById(Integer id);
    
    Page<Product> findByCategory(String id, Pageable pageable);

	List<Product> findAll();

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);
	
	Page<Product> findName(String name, Pageable pageable);
}
