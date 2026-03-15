package e_commerce.clothing_brand.repository;

import e_commerce.clothing_brand.entity.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
