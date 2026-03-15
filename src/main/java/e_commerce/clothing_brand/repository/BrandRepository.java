package e_commerce.clothing_brand.repository;

import e_commerce.clothing_brand.entity.product.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
