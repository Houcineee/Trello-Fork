package com.yollo.services;

import com.yollo.dtos.UserPatchDTO;
import com.yollo.dtos.UserRequestDTO;
import com.yollo.dtos.UserResponseDTO;

public interface UserService {
    UserResponseDTO getUserById(Long id);
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(Long userId , UserPatchDTO userPatchDTO);
    void deleteUser(Long id);

}

