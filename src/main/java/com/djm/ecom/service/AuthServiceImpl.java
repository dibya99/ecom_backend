package com.djm.ecom.service;

import com.djm.ecom.dto.RegisterRequest;
import com.djm.ecom.dto.RegisterResponse;
import com.djm.ecom.entity.User;
import com.djm.ecom.exception.EmailAlreadyExistsException;
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

    public RegisterResponse register(RegisterRequest registerRequest)
    {

        if(userRepository.existsByEmail(registerRequest.getEmail()))
            throw new EmailAlreadyExistsException("Another account registered with same email");

        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();
        User savedUser = userRepository.save(user);
        return RegisterResponse.builder()
                .userId(savedUser.getUserId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .build();

    }
}
