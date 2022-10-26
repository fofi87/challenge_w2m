package com.minData.W2m.domain.exceptions;

import lombok.Data;

@Data
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }
}
