package com.djm.ecom.repository;

import com.djm.ecom.entity.Cart;
import com.djm.ecom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
