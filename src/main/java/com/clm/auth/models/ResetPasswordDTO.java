package com.clm.auth.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResetPasswordDTO {

    private String username;
    private String oldPassword;
    private String newPassword;

}
