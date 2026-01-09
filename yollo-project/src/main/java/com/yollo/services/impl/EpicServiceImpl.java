package com.yollo.services.impl;

import com.yollo.dtos.EpicPatchDTO;
import com.yollo.dtos.EpicRequestDTO;
import com.yollo.dtos.EpicResponseDTO;
import com.yollo.mappers.EpicMapper;
import com.yollo.models.Epic;
import com.yollo.models.ProductBacklog;
import com.yollo.repositories.EpicRepository;
import com.yollo.repositories.ProductRepository;
import com.yollo.services.EpicService;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EpicServiceImpl implements EpicService {
    private final EpicMapper epicMapper;
    private final EpicRepository epicRepository;
    private final ProductRepository productRepository;

    @Override
    public List<EpicResponseDTO> getEpicsByProjectId(Long productId) {
        // check if the project exists
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Project not found");
        }

        List<Epic> epics = epicRepository.findByProductBacklogId(productId);
        return epics.stream()
                .map(epicMapper::toDTO)
                .toList();
    }
    @Override
    public EpicResponseDTO getEpicById(Long epicId) {
        Epic epic = epicRepository.findById(epicId)
                .orElseThrow(() -> new RuntimeException("Epic not found"));

        return epicMapper.toDTO(epic);
    }

    @Override
    public EpicResponseDTO createEpic(Long productId , EpicRequestDTO epicRequestDTO) {
        Epic epic = epicMapper.toEntity(epicRequestDTO);
        ProductBacklog productBacklog = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Backlog not found"));
        epic.setProductBacklog(productBacklog);

        Epic savedEpic = epicRepository.save(epic);
        return epicMapper.toDTO(savedEpic);

    }

    @Override
    public EpicResponseDTO updateEpic(Long epicId, EpicPatchDTO epicPatchDTO) {
        Epic epic = epicRepository.findById(epicId)
                .orElseThrow(() -> new RuntimeException("Epic not found"));

        epicMapper.updateEpicFromPatch(epicPatchDTO, epic);
        return epicMapper.toDTO(epic);
    }

    @Override
    public void deleteEpic(Long epicId) {
        Epic epic = epicRepository.findById(epicId)
                .orElseThrow(() -> new RuntimeException("Epic not found"));
        epicRepository.delete(epic);
    }



}
