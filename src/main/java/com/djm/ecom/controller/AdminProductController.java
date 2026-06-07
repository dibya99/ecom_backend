package com.djm.ecom.controller;

import com.djm.ecom.dto.ProductCreationRequest;
import com.djm.ecom.dto.ProductCreationResponse;
import com.djm.ecom.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
public class AdminProductController {
    private final ProductService productService;

    @PostMapping
    public HttpEntity<ProductCreationResponse> createProduct(@Valid @RequestBody
                                                             ProductCreationRequest productCreationRequest) {
        ProductCreationResponse response = productService.createProduct(productCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{productId}")
    public ResponseEntity<ProductCreationResponse> updateProduct(@PathVariable long productId, @Valid @RequestBody
    ProductCreationRequest productUpdateRequest) {
        ProductCreationResponse productUpdateResponse = productService.updateProduct(productId, productUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(productUpdateResponse);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }


}
