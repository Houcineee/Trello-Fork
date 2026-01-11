package com.yollo.controllers;


import com.yollo.dtos.TaskResponseDTO;
import com.yollo.dtos.UserPatchDTO;
import com.yollo.dtos.UserRequestDTO;
import com.yollo.dtos.UserResponseDTO;
import com.yollo.services.TaskService;
import com.yollo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    final private UserService userService;
    final private TaskService taskService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PreAuthorize("@projectAuth.isTheOwner(#userId, authentication)")
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDTO));

    }

    @PreAuthorize("@projectAuth.isTheOwner(#userId, authentication)")
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId , @RequestBody @Valid UserPatchDTO userPatchDTO){
        return ResponseEntity.ok(userService.updateUser(userId, userPatchDTO));
    }

    @PreAuthorize("@projectAuth.isTheOwner(#userId, authentication)")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("@projectAuth.isTheOwner(#userId, authentication)")
    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<TaskResponseDTO>> getUserTasks(@PathVariable Long userId){
        return ResponseEntity.ok(taskService.getTasksByUserId(userId));
    }


}
