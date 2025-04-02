package com.clm.auth.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {

    String username;
    String password;
    String firstName;
    String lastName;

    @Email(message = "Invalid email format")
    String email;

    @Pattern(regexp = "\\d{10}", message = "Mobile number must be exactly 10 digits")
    String mobile;
}
