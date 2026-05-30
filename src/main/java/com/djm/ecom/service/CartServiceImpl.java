package com.djm.ecom.service;

import com.djm.ecom.dto.AddToCartRequest;
import com.djm.ecom.dto.CartItemResponse;
import com.djm.ecom.dto.CartResponse;
import com.djm.ecom.entity.Cart;
import com.djm.ecom.entity.CartItem;
import com.djm.ecom.entity.Product;
import com.djm.ecom.entity.User;
import com.djm.ecom.exception.ProductNotFoundException;
import com.djm.ecom.repository.CartRepositoy;
import com.djm.ecom.repository.ProductRepository;
import com.djm.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepositoy cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public CartResponse getCart() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            return new UsernameNotFoundException("User not found");
        });
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() ->
                {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    user.setCart(newCart);
                    return cartRepository.save(newCart);
                });
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

    @Override
    public CartResponse addProductToCart(AddToCartRequest addToCartRequest) {
        long productId = addToCartRequest.getProductId();
        int quantity = addToCartRequest.getQuantity();
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            return new UsernameNotFoundException("User not found");
        });
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() ->
                {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    user.setCart(newCart);
                    return cartRepository.save(newCart);
                });
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            throw new ProductNotFoundException("Invalid product Id");
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(optionalProduct.get());
        cartItem.setQuantity(quantity);
        cart.getCartItemList().add(cartItem);
        cartRepository.save(cart);
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
