package com.djm.ecom.service;

import com.djm.ecom.dto.ProductCreationRequest;
import com.djm.ecom.dto.ProductCreationResponse;

public interface ProductService {
    ProductCreationResponse createProduct(ProductCreationRequest productCreationRequest);
}
