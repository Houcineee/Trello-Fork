package com.yollo.services;

import com.yollo.dtos.SprintPatchDTO;
import com.yollo.dtos.SprintRequestDTO;
import com.yollo.dtos.SprintResponseDTO;

import java.util.List;

public interface SprintService {
    List<SprintResponseDTO> getSprintsByProductId(Long productId);
    SprintResponseDTO getSprintById(Long id);
    SprintResponseDTO createSprint(Long productBacklogId , SprintRequestDTO sprintRequestDTO);
    SprintResponseDTO updateSprint(Long sprintId, SprintPatchDTO sprintPatchDTO);
    void deleteSprint(Long sprintId);

}
