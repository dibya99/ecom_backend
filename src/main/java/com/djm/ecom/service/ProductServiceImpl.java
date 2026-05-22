package com.djm.ecom.service;

import com.djm.ecom.dto.ProductCreationRequest;
import com.djm.ecom.dto.ProductCreationResponse;
import com.djm.ecom.entity.Product;
import com.djm.ecom.exception.ProductNotFoundException;
import com.djm.ecom.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

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
                .id(savedProduct.getProductId())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .quantity(savedProduct.getQuantity())
                .price(savedProduct.getPrice())
                .build();
        return response;
    }

    @Override
    public List<ProductCreationResponse> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(product ->
                        ProductCreationResponse.builder()
                                .id(product.getProductId())
                                .name(product.getName())
                                .description(product.getDescription())
                                .price(product.getPrice())
                                .quantity(product.getQuantity())
                                .build()
                )
                .toList();
    }

    @Override
    public ProductCreationResponse getProduct(long productId) {
        Optional<Product> searchedProduct = productRepository.findById(productId);
        if (searchedProduct.isEmpty())
            throw new ProductNotFoundException("Product not found");
        return new ProductCreationResponse()
                .builder()
                .id(searchedProduct.get().getProductId())
                .name(searchedProduct.get().getName())
                .description(searchedProduct.get().getDescription())
                .price(searchedProduct.get().getPrice())
                .quantity(searchedProduct.get().getQuantity())
                .build();
    }
}
