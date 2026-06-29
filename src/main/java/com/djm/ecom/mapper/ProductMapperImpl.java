package com.djm.ecom.mapper;

import com.djm.ecom.dto.ProductCreationResponse;
import com.djm.ecom.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper{
    @Override
    public ProductCreationResponse toProductCreationResponse(Product product) {
        ProductCreationResponse response = ProductCreationResponse.builder()
                .id(product.getProductId())
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
        return response;
    }
}
