package com.yollo.services.impl;

import com.yollo.dtos.SprintPatchDTO;
import com.yollo.dtos.SprintRequestDTO;
import com.yollo.dtos.SprintResponseDTO;
import com.yollo.mappers.SprintMapper;
import com.yollo.models.ProductBacklog;
import com.yollo.models.SprintBacklog;
import com.yollo.repositories.ProductRepository;
import com.yollo.repositories.SprintRepository;
import com.yollo.services.SprintService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.RequiredArgsConstructor;
import com.yollo.exceptions.ResourceNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {
    private final SprintRepository sprintRepository;
    private final SprintMapper sprintMapper;
    private final ProductRepository productRepository ;

    @Override
    public List<SprintResponseDTO> getSprintsByProductId(Long productId) {
        ProductBacklog productBacklog = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductBacklog", "id", productId)) ;

        return productBacklog.getSprintBacklogs()
                .stream()
                .map(sprintMapper::toDTO)
                .toList() ;
    }

    @Override
    public SprintResponseDTO getSprintById(Long id) {
        SprintBacklog sprintBacklog =  sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SprintBacklog", "id", id));
        return sprintMapper.toDTO(sprintBacklog) ;
    }

    @Override
    public SprintResponseDTO createSprint(Long productBacklogId , SprintRequestDTO sprintRequestDTO) {
        SprintBacklog sprintBacklog = sprintMapper.toEntity(sprintRequestDTO) ;
        ProductBacklog productBacklog = productRepository.findById(productBacklogId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductBacklog", "id", productBacklogId)) ;
        sprintBacklog.setProductBacklog(productBacklog);
        sprintBacklog = sprintRepository.save(sprintBacklog) ;
        return sprintMapper.toDTO(sprintBacklog) ;
    }

    @Override
    public SprintResponseDTO updateSprint(Long sprintId, SprintPatchDTO sprintPatchDTO) {
        SprintBacklog sprintBacklog = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new ResourceNotFoundException("SprintBacklog", "id", sprintId)) ;

        sprintMapper.updateSprintFromPatch( sprintPatchDTO, sprintBacklog);

        return sprintMapper.toDTO(sprintBacklog) ;
    }


    @Override
    public void deleteSprint(Long sprintId) {
        SprintBacklog sprintBacklog = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new ResourceNotFoundException("SprintBacklog", "id", sprintId)) ;
        sprintRepository.delete(sprintBacklog);
    }

}
