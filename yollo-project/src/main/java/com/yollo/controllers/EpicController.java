package com.yollo.controllers;

import com.yollo.dtos.*;
import com.yollo.services.EpicService;
import com.yollo.services.UserStoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public EpicResponseDTO getEpicById(@PathVariable Long epicId) {
        return epicService.getEpicById(epicId);
    }



  @PatchMapping("/{epicId}")
    public EpicResponseDTO updateEpic(@PathVariable Long epicId, @RequestBody @Valid EpicPatchDTO epicPatchDTO) {
        return epicService.updateEpic(epicId, epicPatchDTO);
    }


  @DeleteMapping("/{epicId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void deleteEpic(@PathVariable Long epicId) {
        epicService.deleteEpic(epicId);
    }

  @GetMapping("/{epicId}/stories")
    public List<UserStoryResponseDTO> getUserStoriesByEpicId(@PathVariable Long epicId) {
        return userStoryService.getUserStoriesByEpicId(epicId) ;
    }

  @PostMapping("/{epicId}/stories")
    @ResponseStatus(HttpStatus.CREATED)
    public UserStoryResponseDTO createUserStory(@PathVariable Long epicId, @RequestBody @Valid UserStoryRequestDTO userStoryRequestDTO) {
        return userStoryService.createUserStory(epicId, userStoryRequestDTO);
    }


}
