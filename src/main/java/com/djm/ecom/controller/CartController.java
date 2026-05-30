package com.djm.ecom.controller;

import com.djm.ecom.dto.AddToCartRequest;
import com.djm.ecom.dto.CartResponse;
import com.djm.ecom.entity.Cart;
import com.djm.ecom.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponse> getCart() {
        CartResponse cartResponse = cartService.getCart();
        return ResponseEntity.status(HttpStatus.OK).body(cartResponse);

    }

    @PostMapping("/items")
    public ResponseEntity<CartResponse> addProductToCart(@Valid @RequestBody AddToCartRequest addToCartRequest) {
        CartResponse cartResponse = cartService.addProductToCart(addToCartRequest);
        return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
    }
}
