package com.diegobarrioh.akdemia.security.principal;

import com.diegobarrioh.akdemia.util.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ApiKeyPrincipal extends CustomPrincipal {

    public ApiKeyPrincipal(String username, Collection<? extends GrantedAuthority> authorities, String descripcion) {
        super(username,null, authorities);
        setFullname(descripcion);
        this.customer = SecurityUtils.authoritiesContainsRole(getAuthorities(), "ROLE_CUSTOMER");
        this.agent = SecurityUtils.authoritiesContainsRole(getAuthorities(), "ROLE_AGENT");

    }
}
