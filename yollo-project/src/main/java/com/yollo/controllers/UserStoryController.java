package com.yollo.controllers;

import com.yollo.dtos.UserStoryPatchDTO;
import com.yollo.dtos.UserStoryResponseDTO;
import com.yollo.services.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class UserStoryController {
    private final UserStoryService userStoryService;


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

}
