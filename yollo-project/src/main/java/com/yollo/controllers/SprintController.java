package com.yollo.controllers;


import com.yollo.dtos.SprintPatchDTO;
import com.yollo.dtos.SprintResponseDTO;
import com.yollo.dtos.UserStoryResponseDTO;
import com.yollo.services.SprintService;
import com.yollo.services.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
@RequiredArgsConstructor
public class SprintController {
    private final SprintService sprintService;
    private final UserStoryService userStoryService;

    @GetMapping("/{sprintId}")
    public ResponseEntity<SprintResponseDTO> getSprintById(@PathVariable Long sprintId) {
        return ResponseEntity.ok(sprintService.getSprintById(sprintId));
    }

    @GetMapping("/{sprintId}/stories")
    public ResponseEntity<List<UserStoryResponseDTO>> getUserStories(@PathVariable Long sprintId) {
        return ResponseEntity.ok(userStoryService.getUserStoriesBySprintId(sprintId));
    }

    @PreAuthorize("hasAnyRole('SM')")
    @PatchMapping("{sprintId}")
    public ResponseEntity<SprintResponseDTO> updateSprint(@PathVariable Long sprintId, @RequestBody SprintPatchDTO sprintPatchDTO) {
        return ResponseEntity.ok(sprintService.updateSprint(sprintId, sprintPatchDTO));
    }

    @PreAuthorize("hasAnyRole('SM')")
    @DeleteMapping("{sprintId}")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long sprintId) {
        sprintService.deleteSprint(sprintId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
