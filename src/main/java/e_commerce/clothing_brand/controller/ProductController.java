package e_commerce.clothing_brand.controller;

import e_commerce.clothing_brand.entity.Product.Product;
import e_commerce.clothing_brand.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
