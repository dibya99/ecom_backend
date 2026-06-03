package com.djm.ecom.strategy;

import com.djm.ecom.entity.PaymentMethod;

import java.math.BigDecimal;

public interface PaymentStrategy {
    void processPayment(BigDecimal amount);
    PaymentMethod getPaymentMethod();
}
