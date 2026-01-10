package com.yollo.services.impl;

import com.yollo.dtos.ProductPatchDTO;
import com.yollo.dtos.ProductRequestDTO;
import com.yollo.dtos.ProductResponseDTO;
import com.yollo.dtos.UserResponseDTO;
import com.yollo.mappers.ProductMapper;
import com.yollo.mappers.UserMapper;
import com.yollo.models.ProductBacklog;
import com.yollo.models.User;
import com.yollo.repositories.ProductRepository;
import com.yollo.repositories.UserRepository;
import com.yollo.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.RequiredArgsConstructor;
import com.yollo.exceptions.ResourceNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ProductResponseDTO createProduct(Long currentUserId, ProductRequestDTO productRequestDTO) {
        ProductBacklog productBacklog = productMapper.toEntity(productRequestDTO);
        User currentUser = userRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUserId));


        productBacklog.getMembers().add(currentUser);
        ProductBacklog savedProduct = productRepository.save(productBacklog);
        return productMapper.toDTO(savedProduct);
    }


    @Override
    public ProductResponseDTO getProductById(Long productId) {
        ProductBacklog productBacklog = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductBacklog", "id", productId));
        return productMapper.toDTO(productBacklog);
    }

    @Override
    public List<ProductResponseDTO> getProductForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return user.getProductBacklogs()
                .stream()
                .map(productMapper::toDTO)
                .toList();

    }


    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductPatchDTO productPatchDTO) {
        ProductBacklog productBacklog = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductBacklog", "id", productId));

        productMapper.updateProductFromPatch(productPatchDTO, productBacklog);
        return productMapper.toDTO(productBacklog);
    }


    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<UserResponseDTO> getMembers(Long productId) {
        ProductBacklog productBacklog = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductBacklog", "id", productId));

        return productBacklog.getMembers()
                .stream()
                .map(userMapper::toDTO)
                .toList();


    }

    @Override
    public void inviteMember(Long productId, Long userId) {
        ProductBacklog productBacklog = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductBacklog", "id", productId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        // TODO: implement equals and hashcode in User entity
        if (!productBacklog.getMembers().contains(user)) {
            productBacklog.getMembers().add(user);
        }
    }

    @Override
    public void removeMember(Long productId, Long userId) {
        ProductBacklog productBacklog = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductBacklog", "id", productId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        productBacklog.getMembers().remove(user);
    }
}
