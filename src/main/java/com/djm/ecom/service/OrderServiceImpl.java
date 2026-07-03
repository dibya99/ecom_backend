package com.djm.ecom.service;

import com.djm.ecom.dto.AdminOrderResponse;
import com.djm.ecom.dto.OrderCreationRequest;
import com.djm.ecom.dto.OrderResponse;
import com.djm.ecom.entity.*;
import com.djm.ecom.exception.CartItemNotFoundException;
import com.djm.ecom.exception.CartNotFoundException;
import com.djm.ecom.exception.NotEnoughQuantityException;
import com.djm.ecom.mapper.OrderMapper;
import com.djm.ecom.repository.CartRepository;
import com.djm.ecom.repository.OrderRepository;
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
    private final OrderMapper orderMapper;

    private Cart getValidatedCart(User user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow(() ->
                new CartNotFoundException("Cart not found for this user"));
        if (cart.getCartItemList().isEmpty())
            throw new CartItemNotFoundException("The user cart is empty");
        for (CartItem cartItem : cart.getCartItemList()) {
            if (cartItem.getQuantity() > cartItem.getProduct().getQuantity())
                throw new NotEnoughQuantityException("Not enough quantity in inventory");
        }
        return cart;

    }

    private Order createOrder(OrderCreationRequest orderCreationRequest, User user, Cart cart) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        Order order = Order.builder()
                .paymentMethod(orderCreationRequest.getPaymentMethod())
                .user(user)
                .build();
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
        order.setTotalAmount(totalAmount);
        return order;

    }

    private void updateInventory(Order order) {
        for (OrderItem orderItem : order.getOrderItemList()) {
            orderItem.getProduct().setQuantity(orderItem.getProduct().getQuantity() - orderItem.getQuantity());
        }
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderCreationRequest orderCreationRequest) {

        User user = currentUserService.getCurrentUser();
        Cart cart = getValidatedCart(user);
        Order order = createOrder(orderCreationRequest, user, cart);

        PaymentStrategy paymentStrategy = paymentStrategyFactory.
                getStrategy(orderCreationRequest.getPaymentMethod());
        paymentStrategy.processPayment(order.getTotalAmount());

        updateInventory(order);
        orderRepository.save(order);
        cart.getCartItemList().clear();
        cartRepository.save(cart);
        return orderMapper.toOrderResponse(order);


    }

    @Override
    public List<OrderResponse> getOrders() {

        User user = currentUserService.getCurrentUser();
        List<Order> orderList = orderRepository.findByUserOrderByCreatedAtDesc(user);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Order order : orderList) {
            OrderResponse orderResponse = orderMapper.toOrderResponse(order);
            orderResponseList.add(orderResponse);
        }

        return orderResponseList;
    }


    @Override
    public List<AdminOrderResponse> getAllOrders() {
        List<Order> allOrders = orderRepository.findAll();
        List<AdminOrderResponse> adminOrderResponseList = new ArrayList<>();
        for (Order order : allOrders) {
            AdminOrderResponse adminOrderResponse = orderMapper.toAdminOrderResponse(order);
            adminOrderResponseList.add(adminOrderResponse);
        }
        return adminOrderResponseList;

    }


}
