package com.djm.ecom.controller;

import com.djm.ecom.dto.OrderCreationRequest;
import com.djm.ecom.dto.OrderResponse;
import com.djm.ecom.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders() {
        List<OrderResponse> orderResponseList = orderService.getOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseList);
    }

}
