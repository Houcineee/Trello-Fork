package com.yollo.repositories;

import com.yollo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Finds tasks assigned to a specific developer
    List<Task> findByDeveloperId(Long developerId);

    // Finds tasks assigned to a specific tester
    List<Task> findByTesterId(Long testerId);
}
