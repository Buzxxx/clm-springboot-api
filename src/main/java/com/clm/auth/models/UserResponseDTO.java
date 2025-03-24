package com.clm.auth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    String firstName;
    String lastName;
    String username;
    String fullName;
    String email;
}
