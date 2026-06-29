package com.djm.ecom.mapper;

import com.djm.ecom.dto.CartItemResponse;
import com.djm.ecom.dto.CartResponse;
import com.djm.ecom.entity.Cart;
import com.djm.ecom.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapperImpl implements CartMapper {
    @Override
    public CartResponse toResponse(Cart cart) {
        List<CartItemResponse> cartItemResponseList = new ArrayList<>();
        for (CartItem cartItemIter : cart.getCartItemList()) {
            cartItemResponseList.add(CartItemResponse.builder()
                    .productName(cartItemIter.getProduct().getName())
                    .productQuantity(cartItemIter.getQuantity())
                    .productId(cartItemIter.getProduct().getProductId())
                    .build())
            ;
        }
        return CartResponse.builder()
                .cartId(cart.getCartId())
                .itemList(cartItemResponseList)
                .build();
    }
}
