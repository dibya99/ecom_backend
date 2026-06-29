package com.djm.ecom.mapper;

import com.djm.ecom.dto.AdminOrderResponse;
import com.djm.ecom.dto.OrderItemResponse;
import com.djm.ecom.dto.OrderResponse;
import com.djm.ecom.entity.Order;
import com.djm.ecom.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponse toOrderResponse(Order order) {
        OrderResponse orderResponse = OrderResponse.builder()
                .orderId(order.getOrderId())
                .paymentMethod(order.getPaymentMethod())
                .createdAt(order.getCreatedAt())
                .totalPrice(order.getTotalAmount())
                .build();
        for (OrderItem orderItem : order.getOrderItemList()) {
            OrderItemResponse orderItemResponse = OrderItemResponse.builder()
                    .quantity(orderItem.getQuantity())
                    .productId(orderItem.getProduct().getProductId())
                    .productName(orderItem.getProduct().getName())
                    .price(orderItem.getPrice())
                    .build();
            orderResponse.getOrderItemResponseList().add(orderItemResponse);
        }
        return orderResponse;
    }

    @Override
    public AdminOrderResponse toAdminOrderResponse(Order order) {
        AdminOrderResponse adminOrderResponse = AdminOrderResponse.builder()
                .orderId(order.getOrderId())
                .userEmail(order.getUser().getEmail())
                .paymentMethod(order.getPaymentMethod())
                .createdAt(order.getCreatedAt())
                .totalPrice(order.getTotalAmount())
                .build();

        for (OrderItem orderItem : order.getOrderItemList()) {
            OrderItemResponse orderItemResponse = OrderItemResponse.builder()
                    .quantity(orderItem.getQuantity())
                    .productId(orderItem.getProduct().getProductId())
                    .productName(orderItem.getProduct().getName())
                    .price(orderItem.getPrice())
                    .build();
            adminOrderResponse.getOrderItemResponseList().add(orderItemResponse);
        }
        return adminOrderResponse;
    }
}
