package com.yollo.controllers;

import com.yollo.dtos.DevTaskAssignmentDTO;
import com.yollo.dtos.TaskPatchDTO;
import com.yollo.dtos.TaskResponseDTO;
import com.yollo.dtos.TesterTaskAssignmentDTO;
import com.yollo.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    final private TaskService taskService;


    @GetMapping("{taskId}")
    public TaskResponseDTO getTask(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }

    @PatchMapping("{taskId}")
    public TaskResponseDTO updateTask(@PathVariable Long taskId, @RequestBody TaskPatchDTO taskPatchDTO){
        return taskService.updateTask(taskId, taskPatchDTO) ;
    }

    @DeleteMapping("{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
    }

    @PatchMapping("{taskId}/assignDev")
    public void assignTaskToDev(@PathVariable Long taskId , @RequestBody DevTaskAssignmentDTO devTaskAssignmentDTO){
        taskService.assignTaskToDeveloper(taskId,devTaskAssignmentDTO.developerId());
    }

    @PatchMapping("{taskId}/assignTester")
    public void assignTaskToTester(@PathVariable Long taskId , @RequestBody TesterTaskAssignmentDTO testerTaskAssignmentDTO) {
        taskService.assignTaskToTester(taskId, testerTaskAssignmentDTO.testerId());
    }

}


