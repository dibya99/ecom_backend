package com.djm.ecom.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemResponse {
    private long productId;
    private String productName;
    private long productQuantity;

}
