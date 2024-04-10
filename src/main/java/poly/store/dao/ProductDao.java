package poly.store.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import poly.store.entity.Product;
import poly.store.entity.Category;



@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	Page<Product> findByCategory(Category category, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
	Page<Product> findName(@Param("name") String name, Pageable pageable);
}
