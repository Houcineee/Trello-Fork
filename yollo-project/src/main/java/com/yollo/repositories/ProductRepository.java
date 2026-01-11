package com.yollo.repositories;

import com.yollo.models.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductBacklog,Long> {
    // check if the user is a member of the project
   boolean existsByIdAndMembersId(Long productId, Long userId);
}
