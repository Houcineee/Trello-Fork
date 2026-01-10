package com.yollo.controllers;

import com.yollo.dtos.DevTaskAssignmentDTO;
import com.yollo.dtos.TaskPatchDTO;
import com.yollo.dtos.TaskResponseDTO;
import com.yollo.dtos.TesterTaskAssignmentDTO;
import com.yollo.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    final private TaskService taskService;


    @GetMapping("{taskId}")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable Long taskId){
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PreAuthorize("hasAnyRole('SM','DEVELOPER','TESTER')")
    @PatchMapping("{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskPatchDTO taskPatchDTO){
        return ResponseEntity.ok(taskService.updateTask(taskId, taskPatchDTO)) ;
    }

    @PreAuthorize("hasAnyRole('SM')")
    @DeleteMapping("{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyRole('DEVELOPER')")
    @PatchMapping("{taskId}/assignDev")
    public ResponseEntity<Void> assignTaskToDev(@PathVariable Long taskId , @RequestBody DevTaskAssignmentDTO devTaskAssignmentDTO){
        taskService.assignTaskToDeveloper(taskId,devTaskAssignmentDTO.developerId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyRole('TESTER')")
    @PatchMapping("{taskId}/assignTester")
    public ResponseEntity<Void> assignTaskToTester(@PathVariable Long taskId , @RequestBody TesterTaskAssignmentDTO testerTaskAssignmentDTO) {
        taskService.assignTaskToTester(taskId, testerTaskAssignmentDTO.testerId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
