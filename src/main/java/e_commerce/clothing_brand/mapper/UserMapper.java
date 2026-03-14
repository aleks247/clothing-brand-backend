package e_commerce.clothing_brand.mapper;

import e_commerce.clothing_brand.dto.user.SimpleUserDTO;
import e_commerce.clothing_brand.dto.user.UserResponseDTO;
import e_commerce.clothing_brand.entity.User;

public class UserMapper {

    public static UserResponseDTO toDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .enabled(user.getEnabled())
                .accountNonLocked(user.getAccountNonLocked())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static SimpleUserDTO toSimpleDTO(User user) {
        return SimpleUserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}