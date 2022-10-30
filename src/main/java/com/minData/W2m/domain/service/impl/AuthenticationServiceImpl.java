package com.minData.W2m.domain.service.impl;

import com.minData.W2m.app.api.TokenApi;
import com.minData.W2m.domain.service.AuthenticationService;
import com.minData.W2m.domain.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JwtService jwtService;
    @Value("${security.userDummy}")
    private String userDummy;
    @Value("${security.passwordDummy}")
    private String passwordDummy;
    @Value("${security.roleDummy}")
    private String roleDummy;

    @Override
    public TokenApi authenticate() {
        UserDetails userDetails = User.withUsername(userDummy).password(passwordDummy).roles(roleDummy).build();
        return new TokenApi(jwtService.generateToken(userDetails));
    }
}
