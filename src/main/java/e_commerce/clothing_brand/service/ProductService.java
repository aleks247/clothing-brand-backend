package e_commerce.clothing_brand.service;

import e_commerce.clothing_brand.dto.product.ProductRequestDTO;
import e_commerce.clothing_brand.dto.product.ProductResponseDTO;
import e_commerce.clothing_brand.entity.product.*;
import e_commerce.clothing_brand.mapper.ProductMapper;
import e_commerce.clothing_brand.repository.BrandRepository;
import e_commerce.clothing_brand.repository.CategoryRepository;
import e_commerce.clothing_brand.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductResponseDTO createProduct(ProductRequestDTO dto, List<MultipartFile> images) {
        Product product = productMapper.toEntity(dto);

        if (dto.getVariants() != null && !dto.getVariants().isEmpty()) {
            List<ProductVariant> variantList = dto.getVariants().stream().map(vDto -> {
                return ProductVariant.builder()
                        .size(vDto.getSize())
                        .color(vDto.getColor())
                        .stock(vDto.getStock())
                        .sku(vDto.getSku())
                        .product(product)
                        .build();
            }).collect(Collectors.toList());

            product.setVariants(variantList);
        }

        String uploadDir = "uploads/";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdir();
        }

        List<ProductImage> imageList = new ArrayList<>();
        if (images != null && !images.isEmpty()) {
            for (MultipartFile file : images) {
                try {
                    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                    Path filePath = Paths.get(uploadDir + fileName);
                    Files.write(filePath, file.getBytes());

                    ProductImage image = ProductImage.builder()
                            .imageUrl("/uploads/" + fileName)
                            .product(product)
                            .build();
                    imageList.add(image);
                } catch (Exception e) {
                    throw new RuntimeException("Image upload failed");
                }
            }
            product.setImages(imageList);
        }

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDTO(savedProduct);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setGender(dto.getGender());
        product.setTag(dto.getTag());
        product.setBrand(brand);
        product.setCategory(category);
        product.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product);

        return ProductMapper.toDTO(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}