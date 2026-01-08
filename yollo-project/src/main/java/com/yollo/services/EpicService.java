package com.yollo.services;

import com.yollo.dtos.EpicPatchDTO;
import com.yollo.dtos.EpicRequestDTO;
import com.yollo.dtos.EpicResponseDTO;

import java.util.List;

public interface EpicService {
    List<EpicResponseDTO> getEpicsByProjectId(Long productId);
    EpicResponseDTO getEpicById(Long epicId);
    EpicResponseDTO createEpic( Long productId  , EpicRequestDTO epicRequestDTO);
    EpicResponseDTO updateEpic(Long epicId, EpicPatchDTO epicPatchDTO);
    void deleteEpic(Long epicId);
}
