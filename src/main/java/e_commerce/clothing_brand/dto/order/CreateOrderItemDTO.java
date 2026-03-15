package e_commerce.clothing_brand.dto.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderItemDTO {

    private Long productId;
    private Integer quantity;

}