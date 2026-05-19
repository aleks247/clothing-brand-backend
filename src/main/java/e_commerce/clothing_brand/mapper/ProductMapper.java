package e_commerce.clothing_brand.mapper;

import e_commerce.clothing_brand.dto.product.*;
import e_commerce.clothing_brand.entity.product.Product;
import e_commerce.clothing_brand.entity.product.ProductImage;
import e_commerce.clothing_brand.entity.product.ProductVariant;
import org.springframework.stereotype.Component;

import java.util.ArrayList; // 👈 Don't forget this import
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public static ProductResponseDTO toDTO(Product product) {

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .gender(product.getGender())
                .tag(product.getTag())
                .active(product.getActive())
                .brand(product.getBrand() != null ? product.getBrand().getName() : null)
                .category(product.getCategory() != null ? product.getCategory().getName() : null)
                .variants(mapVariants(product.getVariants()))
                .images(mapImages(product.getImages()))
                .build();
    }

    public Product toEntity(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .gender(dto.getGender())
                .tag(dto.getTag())
                .build();
    }

    private static ArrayList<ProductVariantDTO> mapVariants(List<ProductVariant> variants) {
        if (variants == null) {
            return new ArrayList<>();
        }
        return variants.stream().map(v ->
                ProductVariantDTO.builder()
                        .id(v.getId())
                        .size(v.getSize())
                        .color(v.getColor())
                        .stock(v.getStock())
                        .sku(v.getSku())
                        .build()
        ).collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<ProductImageDTO> mapImages(List<ProductImage> images) {
        if (images == null) {
            return new ArrayList<>();
        }
        return images.stream().map(i ->
                ProductImageDTO.builder()
                        .id(i.getId())
                        .imageUrl(i.getImageUrl())
                        .build()
        ).collect(Collectors.toCollection(ArrayList::new));
    }
}