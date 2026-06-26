package com.djm.ecom.service;

import com.djm.ecom.dto.AdminOrderResponse;
import com.djm.ecom.dto.OrderCreationRequest;
import com.djm.ecom.dto.OrderItemResponse;
import com.djm.ecom.dto.OrderResponse;
import com.djm.ecom.entity.*;
import com.djm.ecom.exception.CartItemNotFoundException;
import com.djm.ecom.exception.CartNotFoundException;
import com.djm.ecom.exception.NotEnoughQuantityException;
import com.djm.ecom.repository.CartRepository;
import com.djm.ecom.repository.OrderRepository;
import com.djm.ecom.repository.UserRepository;
import com.djm.ecom.strategy.PaymentStrategy;
import com.djm.ecom.strategy.PaymentStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final PaymentStrategyFactory paymentStrategyFactory;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final CurrentUserService currentUserService;

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderCreationRequest orderCreationRequest) {

        User user = currentUserService.getCurrentUser();
        Cart cart = cartRepository.findByUser(user).orElseThrow(() ->
                new CartNotFoundException("Cart not found for this user"));
        if (cart.getCartItemList().isEmpty())
            throw new CartItemNotFoundException("The user cart is empty");
        for (CartItem cartItem : cart.getCartItemList()) {
            if (cartItem.getQuantity() > cartItem.getProduct().getQuantity())
                throw new NotEnoughQuantityException("Not enough quantity in inventory");
        }
        Order order = Order.builder()
                .paymentMethod(orderCreationRequest.getPaymentMethod())
                .user(user)
                .build();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getCartItemList()) {
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(cartItem.getProduct())
                    .price(cartItem.getProduct().getPrice())
                    .quantity(cartItem.getQuantity())
                    .build();
            order.getOrderItemList().add(orderItem);
            totalAmount = totalAmount.add(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }
        PaymentStrategy paymentStrategy = paymentStrategyFactory.
                getStrategy(orderCreationRequest.getPaymentMethod());
        order.setTotalAmount(totalAmount);
        paymentStrategy.processPayment(totalAmount);
        for (OrderItem orderItem : order.getOrderItemList()) {
            orderItem.getProduct().setQuantity(orderItem.getProduct().getQuantity() - orderItem.getQuantity());
        }
        orderRepository.save(order);
        cart.getCartItemList().clear();
        cartRepository.save(cart);
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
    public List<OrderResponse> getOrders() {

        User user = currentUserService.getCurrentUser();
        List<Order> orderList = orderRepository.findByUserOrderByCreatedAtDesc(user);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Order order : orderList) {
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
            orderResponseList.add(orderResponse);

        }
        return orderResponseList;
    }

    @Override
    public List<AdminOrderResponse> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        List<AdminOrderResponse> adminOrderResponseList = new ArrayList<>();
        for (Order order : allOrders) {
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
            adminOrderResponseList.add(adminOrderResponse);

        }
        return adminOrderResponseList;

    }


}
