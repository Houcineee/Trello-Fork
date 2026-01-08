package com.yollo.controllers;

import com.yollo.dtos.*;
import com.yollo.services.EpicService;
import com.yollo.services.ProductService;
import com.yollo.services.SprintService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public List<ProductResponseDTO> getProductsByUserId(@PathVariable  Long userId) {
        return productService.getProductForUser(userId);
    }

    @PostMapping("user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO createProduct(@PathVariable  Long userId, @RequestBody @Valid ProductRequestDTO productRequestDTO) {
        return productService.createProduct(userId, productRequestDTO);
    }



    @PatchMapping("/{productId}")
    public ProductResponseDTO updateProduct(@PathVariable Long productId, @RequestBody ProductPatchDTO productPatchDTO) {
        return productService.updateProduct(productId, productPatchDTO);
    }


   @GetMapping("/{productId}")
   public ProductResponseDTO getProductById(@PathVariable Long productId) {
         return productService.getProductById(productId);
   }



    @GetMapping("/{productId}/members")
    public List<UserResponseDTO> getMembers(@PathVariable Long productId) {
        return productService.getMembers(productId);
    }

    @PostMapping("/{productId}/members/{userId}")
    public void inviteMember(@PathVariable Long productId, @PathVariable  Long userId) {
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

    @GetMapping("/{productId}/sprints")
    public List<SprintResponseDTO> getSprints(@PathVariable Long productId) {
        return sprintService.getSprintsByProductId(productId);
    }


    @PostMapping("/{productId}/sprints")
    public SprintResponseDTO createSprint(@PathVariable Long productId, @RequestBody @Valid SprintRequestDTO sprintRequestDTO) {
        return sprintService.createSprint(productId, sprintRequestDTO);
    }

    @GetMapping("/{productId}/epics")
    public List<EpicResponseDTO> getEpicsByProjectId(@PathVariable Long productId) {
        return epicService.getEpicsByProjectId(productId);
    }


    @PostMapping("/{productId}/epics")
    @ResponseStatus(HttpStatus.CREATED)
    public EpicResponseDTO createEpic(@PathVariable Long productId, @RequestBody @Valid EpicRequestDTO epicRequestDTO) {
        return epicService.createEpic(productId, epicRequestDTO);
    }
}