package e_commerce.clothing_brand.dto.order;

import e_commerce.clothing_brand.dto.user.SimpleUserDTO;
import e_commerce.clothing_brand.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {

    private Long id;
    private SimpleUserDTO user; // minimal user info
    private List<OrderItemDTO> items;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}