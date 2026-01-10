package com.yollo.controllers;

import com.yollo.dtos.TaskRequestDTO;
import com.yollo.dtos.TaskResponseDTO;
import com.yollo.dtos.UserStoryPatchDTO;
import com.yollo.dtos.UserStoryResponseDTO;
import com.yollo.services.TaskService;
import com.yollo.services.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class UserStoryController {
    private final UserStoryService userStoryService;
    private final TaskService taskService;


    @GetMapping("{storyId}")
    public ResponseEntity<UserStoryResponseDTO> getUserStory(@PathVariable Long storyId) {
        return ResponseEntity.ok(userStoryService.getUserStoryById(storyId));
    }

    @PreAuthorize("hasRole('SM')")
    @PatchMapping("{storyId}")
    public ResponseEntity<UserStoryResponseDTO> updateUserStory(@PathVariable Long storyId, @RequestBody UserStoryPatchDTO userStoryPatchDTO) {
        return ResponseEntity.ok(userStoryService.updateUserStory(storyId,userStoryPatchDTO ));
    }

    @PreAuthorize("hasRole('SM')")
    @PostMapping("{storyId}/sprint/{sprintId}")
    public ResponseEntity<Void> assignUserStoryToSprint(@PathVariable Long storyId, @PathVariable Long sprintId) {
        userStoryService.addUserStoryToSprint(storyId,sprintId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasRole('SM')")
    @DeleteMapping("{storyId}")
    public ResponseEntity<Void> deleteUserStory(@PathVariable Long storyId) {
        userStoryService.deleteUserStory(storyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("{storyId}/tasks")
    public ResponseEntity<List<TaskResponseDTO>> getUserStoryTask(@PathVariable Long storyId) {
        return ResponseEntity.ok(taskService.getTasksByStoryId(storyId));

    }

    @PreAuthorize("hasRole('SM')")
    @PostMapping("{storyId}/tasks")
    public ResponseEntity<TaskResponseDTO> createUserStoryTask(@PathVariable Long storyId, @RequestBody TaskRequestDTO taskRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(storyId, taskRequestDTO));
    }

}
