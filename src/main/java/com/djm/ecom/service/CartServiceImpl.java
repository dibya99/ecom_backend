package com.djm.ecom.service;

import com.djm.ecom.dto.AddToCartRequest;
import com.djm.ecom.dto.CartResponse;
import com.djm.ecom.entity.Cart;
import com.djm.ecom.entity.CartItem;
import com.djm.ecom.entity.Product;
import com.djm.ecom.entity.User;
import com.djm.ecom.exception.CartItemNotFoundException;
import com.djm.ecom.exception.CartNotFoundException;
import com.djm.ecom.exception.NotEnoughQuantityException;
import com.djm.ecom.exception.ProductNotFoundException;
import com.djm.ecom.mapper.CartMapper;
import com.djm.ecom.repository.CartRepository;
import com.djm.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CurrentUserService currentUserService;
    private final CartMapper cartMapper;

    @Override
    public CartResponse getCart() {
        User user = currentUserService.getCurrentUser();
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() ->
                {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    user.setCart(newCart);
                    return cartRepository.save(newCart);
                });
        return cartMapper.toResponse(cart);

    }

    @Override
    public CartResponse addProductToCart(AddToCartRequest addToCartRequest) {
        long productId = addToCartRequest.getProductId();
        int quantity = addToCartRequest.getQuantity();
        User user = currentUserService.getCurrentUser();
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
        CartItem cartItem = null;
        // Business logic fix
        boolean isAlreadyPresent = false;
        for (CartItem item : cart.getCartItemList()) {
            if (item.getProduct().getProductId() == productId) {
                if (item.getQuantity() + quantity > item.getProduct().getQuantity())
                    throw new NotEnoughQuantityException("Not enough quantity");
                cartItem = item;
                isAlreadyPresent = true;
                item.setQuantity(item.getQuantity() + quantity);
                break;
            }
        }

        if (!isAlreadyPresent) {
            if (quantity > optionalProduct.get().getQuantity())
                throw new NotEnoughQuantityException("Not enough quantity");
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(optionalProduct.get());
            cartItem.setQuantity(quantity);
            cart.getCartItemList().add(cartItem);
        }
        cartRepository.save(cart);
        return cartMapper.toResponse(cart);


    }

    @Override
    public CartResponse removeItemFromCart(long productId) {
        User user = currentUserService.getCurrentUser();
        Cart cart = cartRepository.findByUser(user).orElseThrow(() ->
                new CartNotFoundException("Cart not found for this user"));
        CartItem removedItem = null;
        for (CartItem cartItem : cart.getCartItemList()) {
            if (cartItem.getProduct().getProductId() == productId) {
                removedItem = cartItem;
                break;
            }
        }

        if (removedItem == null)
            throw new CartItemNotFoundException("Cart Item found in the cart");

        cart.getCartItemList().remove(removedItem);
        cartRepository.save(cart);

        return cartMapper.toResponse(cart);


    }

    @Override
    public CartResponse removeAllItemsFromCart() {
        User user = currentUserService.getCurrentUser();
        Cart cart = cartRepository.findByUser(user).orElseThrow(() ->
                new CartNotFoundException("Cart not found for this user"));
        cart.getCartItemList().clear();
        cartRepository.save(cart);
        return cartMapper.toResponse(cart);
    }
}
