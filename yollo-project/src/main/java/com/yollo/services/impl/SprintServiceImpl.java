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
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {
    private final SprintRepository sprintRepository;
    private final SprintMapper sprintMapper;
    private final ProductRepository productRepository ;

    @Override
    public List<SprintResponseDTO> findSprintsByProductId(Long productId) {
        ProductBacklog productBacklog = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found")) ;

        return productBacklog.getSprintBacklogs()
                .stream()
                .map(sprintMapper::toDTO)
                .toList() ;
    }

    @Override
    public SprintResponseDTO findSprintById(Long id) {
        SprintBacklog sprintBacklog =  sprintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sprint not found"));
        return sprintMapper.toDTO(sprintBacklog) ;
    }

    @Override
    public SprintResponseDTO createSprint(Long productBacklogId , SprintRequestDTO sprintRequestDTO) {
        SprintBacklog sprintBacklog = sprintMapper.toEntity(sprintRequestDTO) ;
        ProductBacklog productBacklog = productRepository.findById(productBacklogId)
                .orElseThrow(() -> new RuntimeException("Product not found")) ;
        sprintBacklog.setProductBacklog(productBacklog);
        sprintBacklog = sprintRepository.save(sprintBacklog) ;
        return sprintMapper.toDTO(sprintBacklog) ;
    }

    @Override
    public SprintResponseDTO updateSprint(Long sprintId, SprintPatchDTO sprintPatchDTO) {
        SprintBacklog sprintBacklog = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint not found")) ;

        sprintMapper.updateSprintFromPatch( sprintPatchDTO, sprintBacklog);

        return sprintMapper.toDTO(sprintBacklog) ;
    }


    @Override
    public void deleteSprint(Long sprintId) {
        SprintBacklog sprintBacklog = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint not found")) ;
        sprintRepository.delete(sprintBacklog);
    }

}
