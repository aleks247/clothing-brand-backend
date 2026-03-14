package e_commerce.clothing_brand.dto.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleUserDTO {
    private Long id;
    private String username;
}