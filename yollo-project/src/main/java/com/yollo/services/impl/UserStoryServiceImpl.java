package com.yollo.services.impl;


import com.yollo.dtos.UserStoryPatchDTO;
import com.yollo.dtos.UserStoryRequestDTO;
import com.yollo.dtos.UserStoryResponseDTO;
import com.yollo.mappers.UserStoryMapper;
import com.yollo.models.Epic;
import com.yollo.models.SprintBacklog;
import com.yollo.models.UserStory;
import com.yollo.repositories.EpicRepository;
import com.yollo.repositories.SprintRepository;
import com.yollo.repositories.UserStoryRepository;
import com.yollo.services.UserStoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserStoryServiceImpl implements UserStoryService {
    private final UserStoryMapper userStoryMapper;
    private final UserStoryRepository userStoryRepository;
    private final EpicRepository epicRepository;
    private final SprintRepository sprintRepository;

    @Override
    public List<UserStoryResponseDTO> getUserStoriesByEpicId(Long epicId){
        List<UserStory> userStories = userStoryRepository.findByEpicId(epicId);
        return userStories.stream()
                .map(userStoryMapper::toDTO)
                .toList();
    }

    @Override
    public UserStoryResponseDTO getUserStoryById(Long userStoryId) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new RuntimeException("User Story not found"));

        return userStoryMapper.toDTO(userStory);
    }

    @Override
    public UserStoryResponseDTO createUserStory(Long epicId, UserStoryRequestDTO userStoryRequestDTO) {
        UserStory userStory = userStoryMapper.toEntity(userStoryRequestDTO);
        Epic epic = epicRepository.findById(epicId)
                .orElseThrow(() -> new RuntimeException("Epic not found"));
        userStory.setEpic(epic);
        UserStory savedUserStory = userStoryRepository.save(userStory);
        return userStoryMapper.toDTO(savedUserStory);
    }

    @Override
    public UserStoryResponseDTO updateUserStory(Long userStoryId, UserStoryPatchDTO userStoryPatchDTO) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new RuntimeException("User Story not found"));
        userStoryMapper.updateUserStoryFromPatch(userStoryPatchDTO, userStory);
        return userStoryMapper.toDTO(userStory);
    }

    @Override
    public void deleteUserStory(Long userStoryId) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new RuntimeException("User Story not found"));
        userStoryRepository.delete(userStory);
    }

    @Override
    public List<UserStoryResponseDTO> getUserStoriesBySprintId(Long sprintId) {
        SprintBacklog sprintBacklog = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint not found"));
        Set<UserStory> userStories = sprintBacklog.getUserStoriesSprint();
        return userStories.stream()
                .map(userStoryMapper::toDTO)
                .toList();
    }

    @Override
    public void addUserStoryToSprint(Long userStoryId, Long sprintId) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new RuntimeException("User Story not found"));
        SprintBacklog sprintBacklog = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint not found"));

        userStory.getSprintBacklogs().add(sprintBacklog);
    }
}
