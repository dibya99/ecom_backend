package com.djm.ecom.service;

import com.djm.ecom.dto.AdminOrderResponse;
import com.djm.ecom.dto.OrderCreationRequest;
import com.djm.ecom.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(OrderCreationRequest orderCreationRequest);
    List<OrderResponse> getOrders();
    List<AdminOrderResponse> getAllOrders();
}
