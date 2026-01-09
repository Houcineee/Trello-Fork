package com.yollo.controllers;

import com.yollo.dtos.*;
import com.yollo.services.EpicService;
import com.yollo.services.UserStoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/epics") // base url
@RequiredArgsConstructor
public class EpicController {
    private final UserStoryService userStoryService;
    private final EpicService epicService;



    @GetMapping("/{epicId}")
    public ResponseEntity<EpicResponseDTO> getEpicById(@PathVariable Long epicId) {
        return ResponseEntity.ok(epicService.getEpicById(epicId));
    }



  @PatchMapping("/{epicId}")
    public ResponseEntity<EpicResponseDTO> updateEpic(@PathVariable Long epicId, @RequestBody @Valid EpicPatchDTO epicPatchDTO) {
        return ResponseEntity.ok(epicService.updateEpic(epicId, epicPatchDTO));
    }


  @DeleteMapping("/{epicId}")
    public  ResponseEntity<Void> deleteEpic(@PathVariable Long epicId) {
        epicService.deleteEpic(epicId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

  @GetMapping("/{epicId}/stories")
    public ResponseEntity<List<UserStoryResponseDTO>> getUserStoriesByEpicId(@PathVariable Long epicId) {
        return ResponseEntity.ok(userStoryService.getUserStoriesByEpicId(epicId)) ;
    }

  @PostMapping("/{epicId}/stories")
    public ResponseEntity<UserStoryResponseDTO> createUserStory(@PathVariable Long epicId, @RequestBody @Valid UserStoryRequestDTO userStoryRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userStoryService.createUserStory(epicId, userStoryRequestDTO));
    }


}
