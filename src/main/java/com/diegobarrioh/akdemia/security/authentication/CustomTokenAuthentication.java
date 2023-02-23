package com.diegobarrioh.akdemia.security.authentication;

import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class CustomTokenAuthentication extends PreAuthenticatedAuthenticationToken {

    public CustomTokenAuthentication(String token) {
        super(token, "");
    }

    public String getToken() {
        return getPrincipal().toString();
    }
}
