package com.codegym.controller.order;

import com.codegym.model.DTO.OrderDTO;
import com.codegym.model.Order;
import com.codegym.model.Provider;
import com.codegym.model.User;
import com.codegym.service.order.IOrderService;
import com.codegym.service.provider.IProviderService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    @Autowired
    private IUserService userService;

    //    show tất cả order
    @GetMapping("/orders")
    public ResponseEntity<Iterable<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> orderCreate(@RequestBody OrderDTO orderDTO) {
        Provider provider = new Provider();
        provider = providerService.findById(orderDTO.getProviderId()).get();
        User user = new User();
        user = userService.findById(orderDTO.getUserId()).get();
        Order order = new Order();
        order.setProvider(provider);
        order.setStatus("notpaid");
        order.setUser(user);
        order.setId(orderDTO.getId());
        order.setTimeRent(orderDTO.getTimeRent());
        order.setStartTime(orderDTO.getStartTime());
        try {
            orderService.save(order);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //    show order theo khách hàng
    @GetMapping("/user/orders/{id}")
    public ResponseEntity<Iterable<Order>> findAllOrderByRenter(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.getAllOrderByRenter(id), HttpStatus.OK);
    }

    //    show order theo nhà cung cấp
    @GetMapping("/provider/orders/{id}")
    public ResponseEntity<Iterable<Order>> findAllOrderByProvider(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getAllOrderByProvider(id), HttpStatus.OK);
    }

//    show order tên user và name

    //    Tìm kiếm order theo id
    @GetMapping("/orders/{id}")
    public ResponseEntity<Optional<Order>> showOderByUserIdAndProviderId(@PathVariable Long id) {
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

    //    check sửa trạng thái
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> changeStatus(@PathVariable Long id, String status) {
        Optional<Order> orderOptional = orderService.findById(id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order order = orderOptional.get();
//        chưa biết set thẳng hay set như thế nào . còn đây là kiểu ăn sổi
        order.setStatus("paid");
//        order.setStatus(status);
        orderService.save(order);
        return new ResponseEntity<>(orderOptional.get(), HttpStatus.OK);
    }


    //    xem tất cả order có trạng thái đã thuê
    @GetMapping("/completedOrder")
    public ResponseEntity<Iterable<Order>> getAllCompletedOrder() {
        Iterable<Order> orders = orderService.getAllCompletedOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
