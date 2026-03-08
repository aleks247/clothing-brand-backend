package e_commerce.clothing_brand.repository;

import e_commerce.clothing_brand.entity.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
