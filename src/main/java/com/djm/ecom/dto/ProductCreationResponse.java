package com.djm.ecom.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductCreationResponse {
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
}
