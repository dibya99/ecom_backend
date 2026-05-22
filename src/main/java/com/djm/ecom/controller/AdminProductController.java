package com.djm.ecom.controller;

import com.djm.ecom.dto.ProductCreationRequest;
import com.djm.ecom.dto.ProductCreationResponse;
import com.djm.ecom.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin/products")
public class AdminProductController {
    private ProductService productService;

    @PostMapping
    public HttpEntity<ProductCreationResponse> createProduct(@Valid @RequestBody
                                                             ProductCreationRequest productCreationRequest) {
        ProductCreationResponse response = productService.createProduct(productCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
