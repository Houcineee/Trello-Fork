package com.yollo.controllers;

import com.yollo.dtos.*;
import com.yollo.services.EpicService;
import com.yollo.services.UserStoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products/{productId}/epics") // base url
@RequiredArgsConstructor
public class EpicController {
    private final EpicService epicService;



    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'PM')")
    @PostMapping
    public ResponseEntity<EpicResponseDTO> createEpic(@PathVariable Long productId, @RequestBody @Valid EpicRequestDTO epicRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(epicService.createEpic(productId, epicRequestDTO));
    }

    @GetMapping("/{epicId}")
    // auth if the user is a member
    public ResponseEntity<EpicResponseDTO> getEpicById(@PathVariable Long productId , @PathVariable Long epicId) {
        return ResponseEntity.ok(epicService.getEpicById(epicId));
    }



  @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'PM')")
  @PatchMapping("/{epicId}")
    public ResponseEntity<EpicResponseDTO> updateEpic(@PathVariable Long productId ,@PathVariable Long epicId, @RequestBody @Valid EpicPatchDTO epicPatchDTO) {
        return ResponseEntity.ok(epicService.updateEpic(epicId, epicPatchDTO));
    }


   @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'PM')")
   @DeleteMapping("/{epicId}")
    public  ResponseEntity<Void> deleteEpic(@PathVariable Long productId ,@PathVariable Long epicId) {
        epicService.deleteEpic(epicId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping
    // auth if the user is a member
    public ResponseEntity<List<EpicResponseDTO>> getEpicsByProjectId(@PathVariable Long productId) {
        return ResponseEntity.ok(epicService.getEpicsByProjectId(productId));
    }



}
