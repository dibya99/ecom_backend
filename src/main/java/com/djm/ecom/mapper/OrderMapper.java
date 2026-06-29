package com.djm.ecom.mapper;

import com.djm.ecom.dto.AdminOrderResponse;
import com.djm.ecom.dto.OrderResponse;
import com.djm.ecom.entity.Order;

public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);
    AdminOrderResponse toAdminOrderResponse(Order order);

}
