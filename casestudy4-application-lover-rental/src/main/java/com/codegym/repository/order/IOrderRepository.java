package com.codegym.repository.order;

import com.codegym.model.DTO.IOrderDTO;
import com.codegym.model.Order;
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

    @Query(nativeQuery = true, value = "select * from orders where status = 'paid'")
    Iterable<Order> getAllCompletedOrder();
//    Iterable<Order> findAllByProvider(Provider provider);

//    @Query(nativeQuery = true, value = "select orders.start_time, orders.time_rent, orders.status, user.user_name, provider.name from orders, user, provider where orders.user_id = user.id and orders.provider_id = provider.id and orders.id = :id")
//    Iterable<Order> showOderByUserIdAndProviderId( @Param("id") Long providerId);

    @Query(nativeQuery = true, value = "update orders set status = 'paid' where user_id = :id or provider_id = :id and status = 'notpaid'")
    Iterable<Order> changeStatus(@Param("id") Long id);
}
