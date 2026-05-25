package com.djm.ecom.service;

import com.djm.ecom.dto.CartResponse;
import com.djm.ecom.entity.Cart;
import com.djm.ecom.entity.User;
import com.djm.ecom.repository.CartRepositoy;
import com.djm.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepositoy cartRepositoy;
    private final UserRepository userRepository;

    @Override
    public CartResponse getCart() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            return new UsernameNotFoundException("User not found");
        });
        Cart cart = cartRepositoy.findByUser(user)
                .orElseGet(() ->
                {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    user.setCart(newCart);
                    return cartRepositoy.save(newCart);
                });
        return CartResponse.builder()
                .cardId(cart.getCartId())
                .itemList(new ArrayList<>())
                .build();

    }
}
