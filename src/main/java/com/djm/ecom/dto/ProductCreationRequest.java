package com.djm.ecom.dto;


import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductCreationRequest {

    @NotBlank(message = "Name is required")
    @Size(min=2, max=100,message = "Name should be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull
    @DecimalMin(value="0.0", inclusive = false, message = "Price should be greater than 0")
    @Digits(integer = 10, fraction = 2,message = "Invalid price format")
    private BigDecimal price;

    @Min(value = 0,message = "Quantity cannot be negative")
    private int quantity;
}
