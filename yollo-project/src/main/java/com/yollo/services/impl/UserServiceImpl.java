package com.yollo.services.impl;

import com.yollo.dtos.UserPatchDTO;
import com.yollo.dtos.UserRequestDTO;
import com.yollo.dtos.UserResponseDTO;
import com.yollo.mappers.UserMapper;
import com.yollo.models.User;
import com.yollo.repositories.UserRepository;
import com.yollo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;
    final private UserMapper userMapper;
    final private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO createUser(@Valid  UserRequestDTO userRequestDTO) {
        // check if username or email already exists
        String normalizedUsername = userRequestDTO.username().trim().toLowerCase();
        String normalizedEmail = userRequestDTO.email().trim().toLowerCase();
        if (userRepository.existsByUsername(normalizedUsername)) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new RuntimeException("Email already exists");
        }
        User user = userMapper.toEntity(userRequestDTO);
        String hashedPassword = passwordEncoder.encode(userRequestDTO.password());
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserResponseDTO updateUser(Long userId , UserPatchDTO userPatchDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUserFromPatch(userPatchDTO, user);

        return userMapper.toDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
}
