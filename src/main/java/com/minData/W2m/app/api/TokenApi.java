package com.minData.W2m.app.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
@Getter
@AllArgsConstructor
public class TokenApi implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String token;

}
