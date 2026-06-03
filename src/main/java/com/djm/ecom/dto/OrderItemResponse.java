package com.djm.ecom.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderItemResponse {
    private long productId;
    private String productName;
    private long quantity;
    private BigDecimal price;
}
