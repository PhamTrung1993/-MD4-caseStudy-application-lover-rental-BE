package com.codegym.service.order;

import com.codegym.model.Order;
import com.codegym.model.Provider;
import com.codegym.service.IGeneralService;

public interface IOrderService extends IGeneralService<Order> {
    Order save(Order order);
    Iterable<Order> getAllOrder();

    Iterable<Order> getAllOrderByRenter(Long id);

    Iterable<Order> getAllOrderByProvider(Long id);

    void removeOrder(Long id);

    Iterable<Order> findAllByProvider(Provider provider);

    Iterable<Order> getAllCompletedOrder();

//    Iterable<Order> showOderByUserIdAndProviderId(Long id);
}
