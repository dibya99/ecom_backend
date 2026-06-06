package com.djm.ecom.dto;

import com.djm.ecom.entity.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminOrderResponse {
    private long orderId;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;
    @Builder.Default
    private List<OrderItemResponse> orderItemResponseList = new ArrayList<>();
    private BigDecimal totalPrice;
    private String userEmail;
}
