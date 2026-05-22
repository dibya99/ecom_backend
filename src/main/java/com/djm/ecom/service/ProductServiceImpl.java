package com.djm.ecom.service;

import com.djm.ecom.dto.ProductCreationRequest;
import com.djm.ecom.dto.ProductCreationResponse;
import com.djm.ecom.entity.Product;
import com.djm.ecom.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements  ProductService{

    private final ProductRepository productRepository;

    @Override
    public ProductCreationResponse createProduct(ProductCreationRequest productCreationRequest) {
        Product createdProduct = Product.builder()
                .name(productCreationRequest.getName())
                .price(productCreationRequest.getPrice())
                .description(productCreationRequest.getDescription())
                .quantity(productCreationRequest.getQuantity())
                .build();
        Product savedProduct = productRepository.save(createdProduct);
        ProductCreationResponse response = ProductCreationResponse.builder()
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .quantity(savedProduct.getQuantity())
                .price(savedProduct.getPrice())
                .build();
        return response;
    }
}
