package com.yollo.services;

import com.yollo.dtos.ProductPatchDTO;
import com.yollo.dtos.ProductRequestDTO;
import com.yollo.dtos.ProductResponseDTO;
import com.yollo.dtos.UserResponseDTO;
import com.yollo.models.ProductBacklog;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> getProductForUser(Long userId);
    ProductResponseDTO getProductById(Long productId);
    ProductResponseDTO createProduct(Long currentUserId, ProductRequestDTO productRequestDTO);
    ProductResponseDTO updateProduct(Long productId, ProductPatchDTO productPatchDTO);
    void deleteProduct(Long productId);
    List<UserResponseDTO> getMembers(Long productId);
    void inviteMember(Long productId, Long userId);
    void removeMember(Long productId, Long userId);
}
