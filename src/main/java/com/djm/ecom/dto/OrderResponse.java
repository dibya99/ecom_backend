package com.djm.ecom.dto;

import com.djm.ecom.entity.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    private long orderId;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> orderItemResponseList = new ArrayList<>();
    private BigDecimal totalPrice;
}
