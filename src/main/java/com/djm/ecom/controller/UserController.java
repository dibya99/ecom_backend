package com.djm.ecom.controller;

import com.djm.ecom.dto.UserDTO;
import com.djm.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser() {
        UserDTO userDTO = userService.getUserDetails();
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }
}
