package e_commerce.clothing_brand.controller;

import e_commerce.clothing_brand.dto.product.ProductRequestDTO;
import e_commerce.clothing_brand.dto.product.ProductResponseDTO;
import e_commerce.clothing_brand.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping()
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO dto){
        return productService.createProduct(dto);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id,@RequestBody ProductRequestDTO dto){
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}