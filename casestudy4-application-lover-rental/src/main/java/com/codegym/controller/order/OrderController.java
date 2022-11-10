package com.codegym.controller.order;

import com.codegym.model.Order;
import com.codegym.service.order.IOrderService;
import com.codegym.service.provider.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProviderService providerService;

    //    show tất cả order
    @GetMapping("/orders")
    public ResponseEntity<Iterable<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    //    show order theo khách hàng
    @GetMapping("/user/{id}/orders")
    public ResponseEntity<Iterable<Order>> findAllOrderByRenter(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.getAllOrderByRenter(id), HttpStatus.OK);
    }

    //    show order theo nhà cung cấp
    @GetMapping("/provider/{id}/orders")
    public ResponseEntity<Iterable<Order>> findAllOrderByProvider(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getAllOrderByProvider(id), HttpStatus.OK);
    }

    //    Tìm kiếm order theo id
    @GetMapping("/orders/{id}")
    public ResponseEntity<Optional<Order>> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    //    Xóa order theo id
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.findById(id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.removeOrder(id);
        return new ResponseEntity<>(orderOptional.get(), HttpStatus.NO_CONTENT);
    }

    //    sủa trạng thái chưa biết dúng hay sai
    @PutMapping("/orders/{id}/changeStatus")
    public ResponseEntity<Order> changeStatus(@PathVariable Long id, Long statusId) {
        Order order = orderService.findById(id).get();
        order.setStatus(String.valueOf(statusId));
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    //    xem tất cả order có trạng thái đã thuê
    @GetMapping("/completedOrder")
    public ResponseEntity<Iterable<Order>> getAllCompletedOrder() {
        Iterable<Order> orders = orderService.getAllCompletedOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
