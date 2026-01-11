package com.yollo.controllers;

import com.yollo.dtos.*;
import com.yollo.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PreAuthorize("hasRole('PM')")
    @PostMapping("user/{userId}")
    public ResponseEntity<ProductResponseDTO> createProduct(@PathVariable  Long userId, @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(userId, productRequestDTO));
    }



    @PreAuthorize("@projectAuth.isTheOwner(#userId, authentication)")
    @GetMapping("user/{userId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByUserId(@PathVariable  Long userId) {
        return ResponseEntity.ok(productService.getProductForUser(userId));
    }




    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'PM')")
    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductPatchDTO productPatchDTO) {
        return ResponseEntity.ok(productService.updateProduct(productId, productPatchDTO));
    }


    @PreAuthorize("@projectAuth.isMemberOfProject(#productId, authentication)")
   @GetMapping("/{productId}")
   public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long productId) {
         return ResponseEntity.ok(productService.getProductById(productId));
   }


    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'PM')")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

   @PreAuthorize("@projectAuth.isMemberOfProject(#productId, authentication)")
    @GetMapping("/{productId}/members")
    public ResponseEntity<List<UserResponseDTO>> getMembers(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getMembers(productId));
    }



    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'PM', 'SM')")
    @PostMapping("/{productId}/members/{userId}")
    public ResponseEntity<Void> inviteMember(@PathVariable Long productId, @PathVariable  Long userId) {
        productService.inviteMember(productId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PreAuthorize("@projectAuth.hasRoleInProject(#productId, authentication, 'PM', 'SM')")
    @DeleteMapping("/{productId}/members/{userId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long productId, @PathVariable Long userId) {
        productService.removeMember(productId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}