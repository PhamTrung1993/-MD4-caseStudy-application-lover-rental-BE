package com.codegym.service.order;

import com.codegym.model.Order;
import com.codegym.model.Provider;
import com.codegym.repository.order.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepository orderRepository;

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }


    @Override
    public Iterable<Order> getAllOrder() {
        return null;
    }

    @Override
    public Iterable<Order> getAllOrderByRenter(Long id) {
        return orderRepository.getAllOrderByRenter(id);
    }

    @Override
    public Iterable<Order> getAllOrderByProvider(Long id) {
        return orderRepository.getAllOrderByProvider(id);
    }

    @Override
    public void removeOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Iterable<Order> findAllByProvider(Provider provider) {
        return null;
    }

    @Override
    public Iterable<Order> getAllCompletedOrder() {
        return orderRepository.getAllCompletedOrder();
    }

//    @Override
//    public Iterable<Order> showOderByUserIdAndProviderId(Long id) {
//        return  orderRepository.showOderByUserIdAndProviderId(id);
//    }

    @Override
    public void delete(Long id) {

    }
}
