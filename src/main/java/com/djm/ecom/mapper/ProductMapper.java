package com.djm.ecom.mapper;

import com.djm.ecom.dto.ProductCreationResponse;
import com.djm.ecom.entity.Product;

public interface ProductMapper {
    ProductCreationResponse toProductCreationResponse(Product product);
}
