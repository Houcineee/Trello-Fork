package com.yollo.services.impl;


import com.yollo.dtos.TaskPatchDTO;
import com.yollo.dtos.TaskRequestDTO;
import com.yollo.dtos.TaskResponseDTO;
import com.yollo.mappers.TaskMapper;
import com.yollo.models.Task;
import com.yollo.models.User;
import com.yollo.models.UserStory;
import com.yollo.repositories.TaskRepository;
import com.yollo.repositories.UserRepository;
import com.yollo.repositories.UserStoryRepository;
import com.yollo.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;
import com.yollo.exceptions.ResourceNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    final private TaskRepository taskRepository;
    final private UserStoryRepository userStoryRepository;
    final private UserRepository userRepository;
    final private TaskMapper taskMapper;


    @Override
    public List<TaskResponseDTO> getTasksByStoryId(Long storyId) {
        UserStory userStory = userStoryRepository.findById(storyId)
                .orElseThrow(() -> new ResourceNotFoundException("UserStory", "id", storyId)) ;

        return userStory.getTasks()
                .stream()
                .map(taskMapper::toDTO)
                .toList() ;
    }

    @Override
    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
        return taskMapper.toDTO(task) ;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO createTask(Long userStoryId , TaskRequestDTO taskRequestDTO) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new ResourceNotFoundException("UserStory", "id", userStoryId)) ;
        Task task = taskMapper.toEntity(taskRequestDTO) ;
        task.setUserStory(userStory);
        Task savedTask = taskRepository.save(task) ;
        return taskMapper.toDTO(savedTask) ;
    }

    @Override
    public TaskResponseDTO updateTask(Long taskId, TaskPatchDTO taskPatchDTO) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId)) ;

        taskMapper.updateTaskFromPatch(taskPatchDTO, task);
        return taskMapper.toDTO(task) ;
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId)) ;
        taskRepository.delete(task);
    }

    @Override
    public void assignTaskToDeveloper(Long taskId, Long developerId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId)) ;

        if(developerId == null){ // unassigning the developer
            task.setDeveloper(null);
        }else{// assigning the developer
            User developer = userRepository.findById(developerId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", developerId)) ;
            task.setDeveloper(developer);

        }
    }

    @Override
    public void assignTaskToTester(Long taskId, Long testerId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId)) ;

        if (testerId == null){ // unassigning the tester
            task.setTester(null);
        }else{ //   assigning the tester
            User tester= userRepository.findById(testerId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", testerId)) ;

            task.setTester(tester);
        }

    }

    @Override
    public List<TaskResponseDTO> getTasksByUserId(Long userId) {
        List<Task> tasksAsDeveloper = taskRepository.findByDeveloperId(userId);
        List<Task> tasksAsTester = taskRepository.findByTesterId(userId);
        return Stream.concat(tasksAsTester.stream(), tasksAsDeveloper.stream())
                .map(taskMapper::toDTO).toList();
    }
}
