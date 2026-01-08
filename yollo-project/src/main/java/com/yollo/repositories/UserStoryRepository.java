package com.yollo.repositories;

import com.yollo.models.UserStory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoryRepository extends CrudRepository<UserStory,Long> {
    List<UserStory> findByEpicId(Long epicId);
    List<UserStory> findBySprintBacklogs_Id(Long sprintId);
}
