package poly.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poly.store.entity.OrderDetail;

@Repository
public interface DetailDao extends JpaRepository<OrderDetail, Long> {

}
