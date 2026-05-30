package com.djm.ecom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
            (
                    mappedBy = "cart",
                    cascade = CascadeType.ALL,
                    orphanRemoval = true
            )
    private List<CartItem> cartItemList = new ArrayList<>();

}
