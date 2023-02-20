package com.diegobarrioh.akdemia.security.principal;

import com.diegobarrioh.akdemia.util.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public abstract class CustomPrincipal extends User {

    private String fullname;
    boolean customer;
    boolean agent;

    protected CustomPrincipal(String username, Collection<? extends GrantedAuthority> authorities, Collection<? extends GrantedAuthority> grantedAuthorities) {
        super(username,"",authorities);
        customer = SecurityUtils.authoritiesContainsRole(getAuthorities(),"ROLE_CUSTOMER");
        agent = SecurityUtils.authoritiesContainsRole(getAuthorities(),"ROLE_AGENT");
    }

    public boolean isCustomer() {
        return customer;
    }

    public boolean isAgent() {
        return agent;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
