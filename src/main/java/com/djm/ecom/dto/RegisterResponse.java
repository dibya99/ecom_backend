package com.djm.ecom.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
}
