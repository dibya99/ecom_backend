package com.djm.ecom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_item")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;

    private int quantity;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name ="product_id")
    private Product product;
}
