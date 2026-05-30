package com.djm.ecom.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddToCartRequest {

    @Min(value = 1,message = "Product Id should be greater than zero")
    private long productId;

    @Min(value = 1,message = "Quantity should be greater than zero")
    private int quantity;
}
