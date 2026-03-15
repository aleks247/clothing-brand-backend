package e_commerce.clothing_brand.service;

import e_commerce.clothing_brand.dto.user.UserResponseDTO;
import e_commerce.clothing_brand.entity.User;
import e_commerce.clothing_brand.mapper.UserMapper;
import e_commerce.clothing_brand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserMapper.toDTO(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}