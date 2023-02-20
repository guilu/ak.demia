package com.diegobarrioh.akdemia.service;

import com.diegobarrioh.akdemia.domain.entity.ApiKey;
import com.diegobarrioh.akdemia.domain.repository.ApiKeyRepository;
import com.diegobarrioh.akdemia.security.principal.ApiKeyPrincipal;
import com.diegobarrioh.akdemia.security.principal.CustomPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public CustomPrincipal authenticate(String apikey) {
        ApiKey apiKey = apiKeyRepository.findById(apikey).orElseThrow();
        if (apikey == null)
            throw new BadCredentialsException("API_KEY invalida");
        String[] roles = apiKey.getRoles().split(",");
        List<SimpleGrantedAuthority> sgas = new ArrayList<>(roles.length);
        for (String role : roles) {
            sgas.add(new SimpleGrantedAuthority(role.trim()));
        }
        return new ApiKeyPrincipal(apiKey.getUser().getEmail(), sgas, apiKey.getDescripcion());

    }

}
