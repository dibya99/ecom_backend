package com.djm.ecom.controller;

import com.djm.ecom.dto.OrderCreationRequest;
import com.djm.ecom.dto.OrderResponse;
import com.djm.ecom.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderCreationRequest orderCreationRequest) {
        OrderResponse orderResponse = orderService.placeOrder(orderCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }
}
