package com.yollo.controllers;

import com.yollo.dtos.EpicPatchDTO;
import com.yollo.dtos.EpicRequestDTO;
import com.yollo.dtos.EpicResponseDTO;
import com.yollo.services.EpicService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/epics") // base url
public class EpicController {
    EpicService epicService;

    public EpicController(EpicService epicService) {
        this.epicService = epicService;
    }

   @GetMapping("/product/{productId}")
    public List<EpicResponseDTO> getEpicsByProjectId(@PathVariable Long productId) {
       return epicService.getEpicsByProjectId(productId);
    }


    @GetMapping("/{epicId}")
    public EpicResponseDTO getEpicById(@PathVariable Long epicId) {
        return epicService.getEpicById(epicId);
    }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public EpicResponseDTO createEpic(@RequestBody EpicRequestDTO epicRequestDTO) {
         return epicService.createEpic(epicRequestDTO);
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

}
