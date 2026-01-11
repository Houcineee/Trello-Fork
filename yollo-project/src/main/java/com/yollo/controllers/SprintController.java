package com.yollo.controllers;


import com.yollo.dtos.SprintPatchDTO;
import com.yollo.dtos.SprintRequestDTO;
import com.yollo.dtos.SprintResponseDTO;
import com.yollo.dtos.UserStoryResponseDTO;
import com.yollo.services.SprintService;
import com.yollo.services.UserStoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}")
@RequiredArgsConstructor
public class SprintController {
    private final SprintService sprintService;
    private final UserStoryService userStoryService;

    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'SM')")
    @PostMapping("/sprints")
    public ResponseEntity<SprintResponseDTO> createSprint(@PathVariable Long productId, @RequestBody @Valid SprintRequestDTO sprintRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sprintService.createSprint(productId, sprintRequestDTO));
    }

    @PreAuthorize("@projectAuth.isMemberOfProject(#productId, authentication)")
    @GetMapping("sprints/{sprintId}")
    public ResponseEntity<SprintResponseDTO> getSprintById(@PathVariable Long productId , @PathVariable Long sprintId) {
        return ResponseEntity.ok(sprintService.getSprintById(sprintId));
    }

    @PreAuthorize("@projectAuth.isMemberOfProject(#productId, authentication)")
    @GetMapping("sprints/{sprintId}/stories")
    public ResponseEntity<List<UserStoryResponseDTO>> getUserStories(@PathVariable Long productId , @PathVariable Long sprintId) {
        return ResponseEntity.ok(userStoryService.getUserStoriesBySprintId(sprintId));
    }

    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'SM')")
    @PatchMapping("sprints/{sprintId}")
    public ResponseEntity<SprintResponseDTO> updateSprint(@PathVariable Long productId , @PathVariable Long sprintId, @RequestBody SprintPatchDTO sprintPatchDTO) {
        return ResponseEntity.ok(sprintService.updateSprint(sprintId, sprintPatchDTO));
    }

    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'SM')")
    @DeleteMapping("sprints/{sprintId}")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long productId , @PathVariable Long sprintId) {
        sprintService.deleteSprint(sprintId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("@projectAuth.isMemberOfProject(#productId, authentication)")
    @GetMapping("sprints")
    public ResponseEntity<List<SprintResponseDTO>> getSprints(@PathVariable Long productId) {
        return ResponseEntity.ok(sprintService.getSprintsByProductId(productId));
    }



}
