package com.djm.ecom.service;

import com.djm.ecom.dto.ProductCreationResponse;
import com.djm.ecom.entity.Product;
import com.djm.ecom.exception.ProductNotFoundException;
import com.djm.ecom.mapper.ProductMapper;
import com.djm.ecom.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {


    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getProduct_shouldReturnResponse_whenProductExists() {
        // Arrange
        Product product = Product.builder()
                .productId(1L)
                .name("Keyboard")
                .description("Mechanical keyboard")
                .price(new BigDecimal("2500.00"))
                .quantity(10)
                .build();

        ProductCreationResponse expectedResponse = ProductCreationResponse.builder()
                .id(1L)
                .name("Keyboard")
                .description("Mechanical keyboard")
                .price(new BigDecimal("2500.00"))
                .quantity(10)
                .build();

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(productMapper.toProductCreationResponse(product))
                .thenReturn(expectedResponse);

        // Act
        ProductCreationResponse actualResponse = productService.getProduct(1L);

        // Assert
        assertEquals(1L, actualResponse.getId());
        assertEquals("Keyboard", actualResponse.getName());
        assertEquals(new BigDecimal("2500.00"), actualResponse.getPrice());
    }

    @Test
    void getProduct_shouldThrowException_whenProductDoesNotExist() {
        // Arrange
        when(productRepository.findById(99L))
                .thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(
                ProductNotFoundException.class,
                () -> productService.getProduct(99L)
        );
    }

}
