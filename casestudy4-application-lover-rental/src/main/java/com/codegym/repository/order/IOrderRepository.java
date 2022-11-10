package com.codegym.repository.order;

import com.codegym.model.Order;
import com.codegym.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

//    Iterable<Order> findAllOrder();
    @Query(nativeQuery = true, value = "select * from orders where user_id = :demo")
    Iterable<Order> getAllOrderByRenter(@Param("demo") Long id);

    @Query(nativeQuery = true, value = "select * from orders where provider_id = :id")

    Iterable<Order> getAllOrderByProvider(@Param("id") Long id);
}
