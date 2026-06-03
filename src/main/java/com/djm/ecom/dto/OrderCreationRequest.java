package com.djm.ecom.dto;


import com.djm.ecom.entity.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderCreationRequest {
    @NotNull(message = "Payment method cannot be null")
    private PaymentMethod paymentMethod;
}
