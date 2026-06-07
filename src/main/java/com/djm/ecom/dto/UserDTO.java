package com.djm.ecom.dto;

import com.djm.ecom.entity.Role;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
