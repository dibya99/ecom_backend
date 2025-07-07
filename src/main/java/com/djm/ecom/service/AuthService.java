package com.djm.ecom.service;

import com.djm.ecom.dto.RegisterRequest;
import com.djm.ecom.dto.RegisterResponse;
import com.djm.ecom.entity.User;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);
}
