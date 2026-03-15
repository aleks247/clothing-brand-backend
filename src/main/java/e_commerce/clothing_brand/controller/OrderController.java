package e_commerce.clothing_brand.controller;

import e_commerce.clothing_brand.dto.order.CreateOrderDTO;
import e_commerce.clothing_brand.dto.order.OrderResponseDTO;
import e_commerce.clothing_brand.dto.order.UpdateOrderStatusDTO;
import e_commerce.clothing_brand.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/user/{id}")
    public List<OrderResponseDTO> getOrdersByUserId(@PathVariable Long id){
        return orderService.getOrdersByUser(id);
    }

    @PostMapping
    public OrderResponseDTO createOrder(@RequestBody CreateOrderDTO dto) {
        return orderService.createOrder(dto);
    }

    @PutMapping("/{id}/status")
    public OrderResponseDTO updateOrderStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusDTO dto){
        return orderService.updateOrderStatus(id, dto);
    }
}