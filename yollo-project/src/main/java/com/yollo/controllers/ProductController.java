package com.yollo.controllers;

import com.yollo.dtos.*;
import com.yollo.services.EpicService;
import com.yollo.services.ProductService;
import com.yollo.services.SprintService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final SprintService sprintService;
    private final EpicService epicService;


    @GetMapping("user/{userId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByUserId(@PathVariable  Long userId) {
        return ResponseEntity.ok(productService.getProductForUser(userId));
    }

    @PostMapping("user/{userId}")
    public ResponseEntity<ProductResponseDTO> createProduct(@PathVariable  Long userId, @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(userId, productRequestDTO));
    }



    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductPatchDTO productPatchDTO) {
        return ResponseEntity.ok(productService.updateProduct(productId, productPatchDTO));
    }


   @GetMapping("/{productId}")
   public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long productId) {
         return ResponseEntity.ok(productService.getProductById(productId));
   }



    @GetMapping("/{productId}/members")
    public ResponseEntity<List<UserResponseDTO>> getMembers(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getMembers(productId));
    }

    @PostMapping("/{productId}/members/{userId}")
    public ResponseEntity<Void> inviteMember(@PathVariable Long productId, @PathVariable  Long userId) {
        productService.inviteMember(productId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{productId}/members/{userId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long productId, @PathVariable Long userId) {
        productService.removeMember(productId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{productId}/sprints")
    public ResponseEntity<List<SprintResponseDTO>> getSprints(@PathVariable Long productId) {
        return ResponseEntity.ok(sprintService.getSprintsByProductId(productId));
    }


    @PostMapping("/{productId}/sprints")
    public ResponseEntity<SprintResponseDTO> createSprint(@PathVariable Long productId, @RequestBody @Valid SprintRequestDTO sprintRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sprintService.createSprint(productId, sprintRequestDTO));
    }

    @GetMapping("/{productId}/epics")
    public ResponseEntity<List<EpicResponseDTO>> getEpicsByProjectId(@PathVariable Long productId) {
        return ResponseEntity.ok(epicService.getEpicsByProjectId(productId));
    }


    @PostMapping("/{productId}/epics")
    public ResponseEntity<EpicResponseDTO> createEpic(@PathVariable Long productId, @RequestBody @Valid EpicRequestDTO epicRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(epicService.createEpic(productId, epicRequestDTO));
    }
}