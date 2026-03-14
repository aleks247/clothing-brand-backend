package e_commerce.clothing_brand.dto.user;

import e_commerce.clothing_brand.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private Role role;
    private Boolean enabled;
    private Boolean accountNonLocked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}