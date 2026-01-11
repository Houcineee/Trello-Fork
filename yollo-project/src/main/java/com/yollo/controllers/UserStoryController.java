package com.yollo.controllers;

import com.yollo.dtos.*;
import com.yollo.services.TaskService;
import com.yollo.services.UserStoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}/")
@RequiredArgsConstructor
public class UserStoryController {
    private final UserStoryService userStoryService;

    @PreAuthorize("@projectAuth.isMemberOfProject(#productId, authentication)")
    @GetMapping("stories/{storyId}")
    public ResponseEntity<UserStoryResponseDTO> getUserStory(@PathVariable Long productId , @PathVariable Long storyId) {
        return ResponseEntity.ok(userStoryService.getUserStoryById(storyId));
    }


    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'PM')")
    @PostMapping("epics/{epicId}/stories")
    public ResponseEntity<UserStoryResponseDTO> createUserStory(@PathVariable Long productId ,@PathVariable Long epicId, @RequestBody @Valid UserStoryRequestDTO userStoryRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userStoryService.createUserStory(epicId, userStoryRequestDTO));
    }

    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'SM')")
    @PatchMapping("stories/{storyId}")
    public ResponseEntity<UserStoryResponseDTO> updateUserStory(@PathVariable Long productId, @PathVariable Long storyId, @RequestBody UserStoryPatchDTO userStoryPatchDTO) {
        return ResponseEntity.ok(userStoryService.updateUserStory(storyId,userStoryPatchDTO ));
    }



    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'SM')")
    @DeleteMapping("stories/{storyId}")
    public ResponseEntity<Void> deleteUserStory(@PathVariable Long productId , @PathVariable Long storyId) {
        userStoryService.deleteUserStory(storyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("@projectAuth.isMemberOfProject(#productId, authentication)")
    @GetMapping("epics/{epicId}/stories")
    public ResponseEntity<List<UserStoryResponseDTO>> getUserStoriesByEpicId(@PathVariable Long productId ,@PathVariable Long epicId) {
        return ResponseEntity.ok(userStoryService.getUserStoriesByEpicId(epicId)) ;
    }





    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'SM')")
    @PostMapping("stories/{storyId}/sprint/{sprintId}")
    public ResponseEntity<Void> assignUserStoryToSprint(@PathVariable Long productId , @PathVariable Long storyId, @PathVariable Long sprintId) {
        userStoryService.addUserStoryToSprint(storyId,sprintId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
