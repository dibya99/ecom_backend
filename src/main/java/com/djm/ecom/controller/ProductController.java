package com.djm.ecom.controller;

import com.djm.ecom.dto.ProductCreationResponse;
import com.djm.ecom.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductCreationResponse>> getAllProducts() {
        List<ProductCreationResponse> productCreationResponseList = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productCreationResponseList);
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductCreationResponse> getProduct(@PathVariable long productId) {
        ProductCreationResponse response = productService.getProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
