package com.yollo.repositories;

import com.yollo.models.SprintBacklog;
import com.yollo.models.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SprintRepository  extends JpaRepository<SprintBacklog,Long> {
}
