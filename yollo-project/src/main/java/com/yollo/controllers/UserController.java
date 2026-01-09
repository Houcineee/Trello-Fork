package com.yollo.controllers;


import com.yollo.dtos.TaskResponseDTO;
import com.yollo.dtos.UserPatchDTO;
import com.yollo.dtos.UserRequestDTO;
import com.yollo.dtos.UserResponseDTO;
import com.yollo.models.Task;
import com.yollo.services.TaskService;
import com.yollo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    final private UserService userService;
    final private TaskService taskService;

    @GetMapping("/{userId}")
    public UserResponseDTO getUser(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return userService.createUser(userRequestDTO);

    }

    @PatchMapping("/{userId}")
    public UserResponseDTO updateUser(@PathVariable Long userId , @RequestBody UserPatchDTO userPatchDTO){
        return userService.updateUser(userId, userPatchDTO);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/{userId}/tasks")
    public List<TaskResponseDTO> getUserTasks(@PathVariable Long userId){
        return taskService.getTasksByUserId(userId);
    }


}
