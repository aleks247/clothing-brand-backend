package e_commerce.clothing_brand.dto.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantDTO {

    private Long id;
    private String size;
    private String color;
    private Integer stock;
    private String sku;

}