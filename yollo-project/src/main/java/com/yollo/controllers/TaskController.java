package com.yollo.controllers;

import com.yollo.dtos.*;
import com.yollo.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}/")
@RequiredArgsConstructor
public class TaskController {
    final private TaskService taskService;

    @PreAuthorize("@projectAuth.isMemberOfProject(#productId, authentication)")
    @GetMapping("stories/{storyId}/tasks")
    public ResponseEntity<List<TaskResponseDTO>> getUserStoryTask(@PathVariable Long productId , @PathVariable Long storyId) {
        return ResponseEntity.ok(taskService.getTasksByStoryId(storyId));

    }


    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'SM')")
    @PostMapping("stories/{storyId}/tasks")
    public ResponseEntity<TaskResponseDTO> createTask(@PathVariable Long productId , @PathVariable Long storyId, @RequestBody TaskRequestDTO taskRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(storyId, taskRequestDTO));
    }

    @PreAuthorize("@projectAuth.isMemberOfProject(#productId, authentication)")
    @GetMapping("tasks/{taskId}")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable Long productId ,@PathVariable Long taskId){
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }


    @PreAuthorize("@projectAuth.isMemberOfProject(#productId, authentication)")
    @PatchMapping("tasks/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long productId , @PathVariable Long taskId, @RequestBody TaskPatchDTO taskPatchDTO){
        return ResponseEntity.ok(taskService.updateTask(taskId, taskPatchDTO)) ;
    }

    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'SM')")
    @DeleteMapping("tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long productId , @PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'DEVELOPER')")
    @PatchMapping("tasks/{taskId}/assignDev")
    public ResponseEntity<Void> assignTaskToDev(@PathVariable Long productId , @PathVariable Long taskId , @RequestBody DevTaskAssignmentDTO devTaskAssignmentDTO){
        taskService.assignTaskToDeveloper(taskId,devTaskAssignmentDTO.developerId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'TESTER')")
    @PatchMapping("tasks/{taskId}/assignTester")
    public ResponseEntity<Void> assignTaskToTester(@PathVariable Long productId , @PathVariable Long taskId , @RequestBody TesterTaskAssignmentDTO testerTaskAssignmentDTO) {
        taskService.assignTaskToTester(taskId, testerTaskAssignmentDTO.testerId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
