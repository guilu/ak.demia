package com.diegobarrioh.akdemia.security;

import com.diegobarrioh.akdemia.domain.entity.ApiKey;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class ApiKeyAuthentication implements Authentication {

    private ApiKey apiKey;

    public ApiKeyAuthentication(ApiKey apikey) {
        this.apiKey = apikey;
    }

    @Override
    public String getName() {
        return apiKey.getName();
    }

    @Override
    public Object getPrincipal() {
        //Maybe a richer object but maybe only the name
        return getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_APIKEY");
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw  new IllegalArgumentException("Don't do this");
    }
}
