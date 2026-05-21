package e_commerce.clothing_brand.dto.auth;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class AuthResponse {
    private String token;
    private String email;
    private String username;
    private String role;
    private Long userId;
}
