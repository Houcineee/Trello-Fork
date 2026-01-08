package com.yollo.controllers;

import com.yollo.dtos.ProductPatchDTO;
import com.yollo.dtos.ProductRequestDTO;
import com.yollo.dtos.ProductResponseDTO;
import com.yollo.dtos.UserResponseDTO;
import com.yollo.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;


    ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("user/{userId}")
    public List<ProductResponseDTO> getProductsByUserId(@PathVariable  Long userId) {
        return productService.getProductForUser(userId);
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@PathVariable  Long userId, @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return productService.createProduct(userId, productRequestDTO);
    }


    @GetMapping("/{productId}/members")
    public List<UserResponseDTO> getMembers(@PathVariable Long productId) {
        return productService.getMembers(productId);
    }

    @PatchMapping("/{productId}")
    public ProductResponseDTO updateProduct(@PathVariable Long productId, @RequestBody ProductPatchDTO productPatchDTO) {
        return productService.updateProduct(productId, productPatchDTO);
    }

    @PostMapping("/{productId}/members")
    public void inviteMember(@PathVariable Long productId, @RequestBody Long userId) {
        productService.inviteMember(productId, userId);
    }

    @DeleteMapping("/{productId}/members/{userId}")
    public void removeMember(@PathVariable Long productId, @PathVariable Long userId) {
        productService.removeMember(productId, userId);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}