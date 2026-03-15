package e_commerce.clothing_brand.service;

import e_commerce.clothing_brand.dto.product.ProductRequestDTO;
import e_commerce.clothing_brand.dto.product.ProductResponseDTO;
import e_commerce.clothing_brand.entity.product.Brand;
import e_commerce.clothing_brand.entity.product.Category;
import e_commerce.clothing_brand.entity.product.Product;
import e_commerce.clothing_brand.enums.Gender;
import e_commerce.clothing_brand.enums.ProductTag;
import e_commerce.clothing_brand.mapper.ProductMapper;
import e_commerce.clothing_brand.repository.BrandRepository;
import e_commerce.clothing_brand.repository.CategoryRepository;
import e_commerce.clothing_brand.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

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

    public ProductResponseDTO createProduct(ProductRequestDTO dto) {

        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .gender(Gender.valueOf(dto.getGender()))
                .tag(ProductTag.valueOf(dto.getTag()))
                .brand(brand)
                .category(category)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        productRepository.save(product);

        return ProductMapper.toDTO(product);
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
        product.setGender(Gender.valueOf(dto.getGender()));
        product.setTag(ProductTag.valueOf(dto.getTag()));
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