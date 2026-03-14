package e_commerce.clothing_brand.dto.product;

import e_commerce.clothing_brand.enums.Gender;
import e_commerce.clothing_brand.enums.ProductTag;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Gender gender;

    private ProductTag tag;

    private Boolean active;

    private String brand;

    private String category;

    private List<ProductVariantDTO> variants;

    private List<ProductImageDTO> images;

}