package com.djm.ecom.service;

import com.djm.ecom.dto.OrderCreationRequest;
import com.djm.ecom.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    @Override
    public OrderResponse placeOrder(OrderCreationRequest orderCreationRequest) {
        return null;
    }
}
