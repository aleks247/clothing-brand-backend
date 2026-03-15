package e_commerce.clothing_brand.dto.order;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderDTO {

    private Long userId;
    private List<CreateOrderItemDTO> items;

}