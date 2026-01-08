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
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EpicServiceImpl implements EpicService {
    EpicMapper epicMapper;
    EpicRepository epicRepository;
    ProductRepository productRepository;

    public EpicServiceImpl(EpicMapper epicMapper, EpicRepository epicRepository , ProductRepository productRepository) {
        this.epicMapper = epicMapper;
        this.epicRepository = epicRepository;
        this.productRepository = productRepository;
    }
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
    public EpicResponseDTO createEpic(EpicRequestDTO epicRequestDTO) {
        Epic epic = epicMapper.toEntity(epicRequestDTO);
        ProductBacklog productBacklog = productRepository.findById(epicRequestDTO.productBacklogId())
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
