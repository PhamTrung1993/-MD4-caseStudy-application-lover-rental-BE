package com.codegym.repository.order;

import com.codegym.model.Order;
import com.codegym.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface IOrderRepository extends JpaRepository<Order, Long> {

}
