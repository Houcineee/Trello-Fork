package com.yollo.controllers;

import com.yollo.dtos.TaskRequestDTO;
import com.yollo.dtos.TaskResponseDTO;
import com.yollo.dtos.UserStoryPatchDTO;
import com.yollo.dtos.UserStoryResponseDTO;
import com.yollo.services.TaskService;
import com.yollo.services.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class UserStoryController {
    private final UserStoryService userStoryService;
    private final TaskService taskService;


    @GetMapping("{storyId}")
    public UserStoryResponseDTO getUserStory(@PathVariable Long storyId) {
        return userStoryService.getUserStoryById(storyId);
    }

    @PatchMapping("{storyId}")
    public UserStoryResponseDTO updateUserStory(@PathVariable Long storyId, @RequestBody UserStoryPatchDTO userStoryPatchDTO) {
        return userStoryService.updateUserStory(storyId,userStoryPatchDTO );
    }

    @PostMapping("{storyId}/sprint/{sprintId}")
    public void assignUserStoryToSprint(@PathVariable Long storyId, @PathVariable Long sprintId) {
        userStoryService.addUserStoryToSprint(storyId,sprintId);
    }

    @DeleteMapping("{storyId}")
    public void deleteUserStory(@PathVariable Long storyId) {
        userStoryService.deleteUserStory(storyId);
    }


    @GetMapping("{storyId}/tasks")
    public List<TaskResponseDTO> getUserStoryTask(@PathVariable Long storyId) {
        return taskService.getTasksByStoryId(storyId);

    }

    @PostMapping("{storyId}/tasks")
    public TaskResponseDTO createUserStoryTask(@PathVariable Long storyId, @RequestBody TaskRequestDTO taskRequestDTO) {
        return taskService.createTask(storyId, taskRequestDTO);
    }

}
