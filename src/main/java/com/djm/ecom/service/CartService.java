package com.djm.ecom.service;

import com.djm.ecom.dto.AddToCartRequest;
import com.djm.ecom.dto.CartResponse;

public interface CartService {
    public CartResponse getCart();
    public CartResponse addProductToCart(AddToCartRequest addToCartRequest);
}
