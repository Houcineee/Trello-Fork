package com.yollo.services;

import com.yollo.dtos.TaskPatchDTO;
import com.yollo.dtos.TaskRequestDTO;
import com.yollo.dtos.TaskResponseDTO;
import com.yollo.models.Task;

import java.util.List;

public interface TaskService {
    List<TaskResponseDTO> getTasksByStoryId(Long storyId);
    TaskResponseDTO getTaskById(Long id);
    TaskResponseDTO createTask(Long userStoryId , TaskRequestDTO taskRequestDTO);
    TaskResponseDTO updateTask(Long taskId, TaskPatchDTO taskPatchDTO);
    void deleteTask(Long taskId);
    void assignTaskToDeveloper(Long taskId, Long developerId);
    void assignTaskToTester(Long taskId, Long testerId);
}
