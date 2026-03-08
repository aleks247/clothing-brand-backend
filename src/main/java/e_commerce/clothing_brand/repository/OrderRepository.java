package e_commerce.clothing_brand.repository;


import e_commerce.clothing_brand.entity.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
