package e_commerce.clothing_brand.service;

import e_commerce.clothing_brand.dto.order.CreateOrderDTO;
import e_commerce.clothing_brand.dto.order.CreateOrderItemDTO;
import e_commerce.clothing_brand.dto.order.OrderResponseDTO;
import e_commerce.clothing_brand.dto.order.UpdateOrderStatusDTO;
import e_commerce.clothing_brand.entity.User;
import e_commerce.clothing_brand.entity.order.Order;
import e_commerce.clothing_brand.entity.order.OrderItem;
import e_commerce.clothing_brand.entity.product.Product;
import e_commerce.clothing_brand.enums.OrderStatus;
import e_commerce.clothing_brand.mapper.OrderMapper;
import e_commerce.clothing_brand.repository.OrderRepository;
import e_commerce.clothing_brand.repository.ProductRepository;
import e_commerce.clothing_brand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<OrderResponseDTO> getAllOrders() {

        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return OrderMapper.toDTO(order);
    }

    public List<OrderResponseDTO> getOrdersByUser(Long userId) {

        return orderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO createOrder(CreateOrderDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = Order.builder()
                .user(user)
//               Order status by default->
                .status(OrderStatus.PROCESSING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        BigDecimal total = BigDecimal.ZERO;

        List<OrderItem> items = new ArrayList<>();

        for (CreateOrderItemDTO itemDTO : dto.getItems()) {

            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            BigDecimal price = product.getPrice();

            OrderItem item = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemDTO.getQuantity())
                    .price(price)
                    .build();

            total = total.add(price.multiply(BigDecimal.valueOf(itemDTO.getQuantity())));

            items.add(item);
        }

        order.setItems(items);
        order.setTotalAmount(total);

        orderRepository.save(order);

        return OrderMapper.toDTO(order);
    }

    public OrderResponseDTO updateOrderStatus(Long orderId, UpdateOrderStatusDTO dto) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(dto.getStatus());
        order.setUpdatedAt(LocalDateTime.now());

        orderRepository.save(order);

        return OrderMapper.toDTO(order);
    }

}