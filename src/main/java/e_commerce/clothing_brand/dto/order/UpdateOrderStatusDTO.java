package e_commerce.clothing_brand.dto.order;

import e_commerce.clothing_brand.enums.OrderStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderStatusDTO {

    private OrderStatus status;

}