package e_commerce.clothing_brand.entity;

import e_commerce.clothing_brand.entity.Order.Order;
import e_commerce.clothing_brand.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String password; // Spring Security encrypted

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean enabled = true;
    private Boolean accountNonLocked = true;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}