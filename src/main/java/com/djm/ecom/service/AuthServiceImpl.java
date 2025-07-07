package com.djm.ecom.service;

import com.djm.ecom.dto.RegisterRequest;
import com.djm.ecom.entity.User;
import com.djm.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(RegisterRequest registerRequest)
    {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();
        user = userRepository.save(user);
        return user;

    }
}
