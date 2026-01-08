package com.yollo.repositories;


import com.yollo.models.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpicRepository extends JpaRepository<Epic,Long> {
    List<Epic> findByProductBacklogId(Long productId);

}
