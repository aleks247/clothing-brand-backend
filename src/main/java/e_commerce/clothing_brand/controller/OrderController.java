package e_commerce.clothing_brand.controller;

import e_commerce.clothing_brand.dto.order.OrderResponseDTO;
import e_commerce.clothing_brand.mapper.OrderMapper;
import e_commerce.clothing_brand.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(OrderMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}