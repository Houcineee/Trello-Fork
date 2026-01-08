package com.yollo.repositories;

import com.yollo.models.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductBacklog,Long> {
}
