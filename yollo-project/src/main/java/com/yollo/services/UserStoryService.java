package com.yollo.services;


import com.yollo.dtos.UserStoryPatchDTO;
import com.yollo.dtos.UserStoryRequestDTO;
import com.yollo.dtos.UserStoryResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserStoryService {
    List<UserStoryResponseDTO> getUserStoriesByEpicId(Long epicId);
    UserStoryResponseDTO getUserStoryById(Long userStoryId);
    UserStoryResponseDTO createUserStory(Long epicId, UserStoryRequestDTO userStoryRequestDTO);
    UserStoryResponseDTO updateUserStory(Long userStoryId, UserStoryPatchDTO userStoryPatchDTO);
    void deleteUserStory(Long userStoryId);
    List<UserStoryResponseDTO> getUserStoriesBySprintId(Long sprintId);
    void addUserStoryToSprint(Long userStoryId, Long sprintId);


}
