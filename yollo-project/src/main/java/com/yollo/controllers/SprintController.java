package com.yollo.controllers;


import com.yollo.dtos.SprintPatchDTO;
import com.yollo.dtos.SprintResponseDTO;
import com.yollo.dtos.UserStoryResponseDTO;
import com.yollo.services.SprintService;
import com.yollo.services.UserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
@RequiredArgsConstructor
public class SprintController {
    private final SprintService sprintService;
    private final UserStoryService userStoryService;

    @GetMapping("/{sprintId}")
    public SprintResponseDTO getSprintById(@PathVariable Long sprintId) {
        return sprintService.getSprintById(sprintId);
    }

    @GetMapping("/{sprintId}/stories")
    public List<UserStoryResponseDTO> getUserStories(@PathVariable Long sprintId) {
        return userStoryService.getUserStoriesBySprintId(sprintId);
    }

    @PatchMapping("{sprintId}")
    public SprintResponseDTO updateSprint(@PathVariable Long sprintId, @RequestBody SprintPatchDTO sprintPatchDTO) {
        return sprintService.updateSprint(sprintId, sprintPatchDTO);
    }

    @DeleteMapping("{sprintId}")
    public void deleteSprint(@PathVariable Long sprintId) {
        sprintService.deleteSprint(sprintId);
    }
}
