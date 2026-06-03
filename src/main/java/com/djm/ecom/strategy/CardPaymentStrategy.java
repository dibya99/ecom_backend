package com.djm.ecom.strategy;

import com.djm.ecom.entity.PaymentMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CardPaymentStrategy implements PaymentStrategy{

    @Override
    public void processPayment(BigDecimal amount) {
        return;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.CREDIT_CARD;
    }


}
