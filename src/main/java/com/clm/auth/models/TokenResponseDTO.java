package com.clm.auth.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponseDTO {

    String accessToken;
    String refreshToken;
}
