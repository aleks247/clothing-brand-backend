package e_commerce.clothing_brand.dto.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageDTO {

    private Long id;
    private String imageUrl;

}
