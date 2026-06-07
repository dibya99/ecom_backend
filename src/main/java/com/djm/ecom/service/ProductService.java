package com.djm.ecom.service;

import com.djm.ecom.dto.ProductCreationRequest;
import com.djm.ecom.dto.ProductCreationResponse;

import java.util.List;

public interface ProductService {
    ProductCreationResponse createProduct(ProductCreationRequest productCreationRequest);
    List<ProductCreationResponse> getAllProducts();
    ProductCreationResponse getProduct(long productId);
    ProductCreationResponse updateProduct(long productId, ProductCreationRequest productUpdateRequest);
    void deleteProduct(long productId);
}
