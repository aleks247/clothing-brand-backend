package e_commerce.clothing_brand.controller;

import e_commerce.clothing_brand.entity.Order.Order;
import e_commerce.clothing_brand.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("orders")
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
