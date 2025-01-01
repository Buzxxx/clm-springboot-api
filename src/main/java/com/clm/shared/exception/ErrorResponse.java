package com.clm.shared.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {

    int status;
    String body;
}
