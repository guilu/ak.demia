package com.diegobarrioh.akdemia.service;

import com.diegobarrioh.akdemia.domain.entity.ApiKey;
import com.diegobarrioh.akdemia.domain.entity.Role;
import com.diegobarrioh.akdemia.domain.repository.ApiKeyRepository;
import com.diegobarrioh.akdemia.security.principal.ApiKeyPrincipal;
import com.diegobarrioh.akdemia.security.principal.CustomPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public CustomPrincipal authenticate(String apikey) {
        ApiKey apiKey = apiKeyRepository.findById(apikey).orElseThrow();
        if (apiKey == null) {
            throw new BadCredentialsException("API_KEY invalida");
        }
        List<SimpleGrantedAuthority> sgas = new ArrayList<>();
        for (Role role : apiKey.getRoles()) {
            sgas.add(new SimpleGrantedAuthority(role.getName().trim()));
        }
        return new ApiKeyPrincipal(apiKey.getKeyValue(), sgas, "");

    }

}
