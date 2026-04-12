package e_commerce.clothing_brand.controller;

import e_commerce.clothing_brand.dto.product.ProductRequestDTO;
import e_commerce.clothing_brand.dto.product.ProductResponseDTO;
import e_commerce.clothing_brand.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

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

    @PostMapping(consumes = "multipart/form-data")
    public ProductResponseDTO createProduct(@RequestPart("product") String productJson, @RequestPart("images") List<MultipartFile> images) {
        ObjectMapper mapper = new ObjectMapper();
        ProductRequestDTO productDTO = mapper.readValue(productJson, ProductRequestDTO.class);
        return productService.createProduct(productDTO, images);
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