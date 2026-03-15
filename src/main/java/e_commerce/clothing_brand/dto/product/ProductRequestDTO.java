package e_commerce.clothing_brand.dto.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private String gender;
    private String tag;
    private Long brandId;
    private Long categoryId;
}