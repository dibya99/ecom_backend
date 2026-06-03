package com.djm.ecom.strategy;

import com.djm.ecom.entity.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentStrategyFactory {
    private Map<PaymentMethod, PaymentStrategy> strategyMap;

    public PaymentStrategyFactory(List<PaymentStrategy> strategyList) {
        strategyMap = new HashMap<>();
        for (PaymentStrategy strategy : strategyList) {
            strategyMap.put(strategy.getPaymentMethod(), strategy);
        }
    }

    public PaymentStrategy getStrategy(PaymentMethod paymentMethod) {
        return strategyMap.get(paymentMethod);
    }


}
