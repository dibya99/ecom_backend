package com.djm.ecom.service;

import com.djm.ecom.dto.UserDTO;
import com.djm.ecom.entity.User;
import com.djm.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CurrentUserService currentUserService;

    @Override
    public UserDTO getUserDetails() {
        User user = currentUserService.getCurrentUser();
        return UserDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .email(user.getEmail())
                .build();
    }
}
