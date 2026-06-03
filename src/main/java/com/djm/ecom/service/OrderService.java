package com.djm.ecom.service;

import com.djm.ecom.dto.OrderCreationRequest;
import com.djm.ecom.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderCreationRequest orderCreationRequest);
}
