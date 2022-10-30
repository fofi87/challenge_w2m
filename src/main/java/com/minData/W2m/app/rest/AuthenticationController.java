package com.minData.W2m.app.rest;

import com.minData.W2m.app.api.TokenApi;
import com.minData.W2m.domain.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/authenticate")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping()
    public ResponseEntity<TokenApi> authenticate() {
        return new ResponseEntity<>(this.authenticationService.authenticate(), HttpStatus.OK);
    }
}
