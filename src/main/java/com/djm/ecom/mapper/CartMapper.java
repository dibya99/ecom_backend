package com.djm.ecom.mapper;

import com.djm.ecom.dto.CartResponse;
import com.djm.ecom.entity.Cart;

public interface CartMapper {
    public CartResponse toResponse(Cart cart);
}
