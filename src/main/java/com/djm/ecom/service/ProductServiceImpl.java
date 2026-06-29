package com.djm.ecom.service;

import com.djm.ecom.dto.ProductCreationRequest;
import com.djm.ecom.dto.ProductCreationResponse;
import com.djm.ecom.entity.Product;
import com.djm.ecom.exception.ProductNotFoundException;
import com.djm.ecom.mapper.ProductMapper;
import com.djm.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductCreationResponse createProduct(ProductCreationRequest productCreationRequest) {
        Product createdProduct = Product.builder()
                .name(productCreationRequest.getName())
                .price(productCreationRequest.getPrice())
                .description(productCreationRequest.getDescription())
                .quantity(productCreationRequest.getQuantity())
                .build();
        Product savedProduct = productRepository.save(createdProduct);
        return productMapper.toProductCreationResponse(savedProduct);
    }

    @Override
    public List<ProductCreationResponse> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(productMapper::toProductCreationResponse)
                .toList();
    }

    @Override
    public ProductCreationResponse getProduct(long productId) {
        Product searchedProduct = productRepository.findById(productId).
                orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productMapper.toProductCreationResponse(searchedProduct);
    }

    @Override
    public ProductCreationResponse updateProduct(long productId, ProductCreationRequest productUpdateRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with given Id not found"));
        product.setName(productUpdateRequest.getName());
        product.setDescription(productUpdateRequest.getDescription());
        product.setQuantity(productUpdateRequest.getQuantity());
        product.setPrice(productUpdateRequest.getPrice());
        Product updatedProduct = productRepository.save(product);
        return productMapper.toProductCreationResponse(updatedProduct);

    }

    @Override
    public void deleteProduct(long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with given id not found"));
        productRepository.delete(product);
    }
}
