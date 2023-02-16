package com.diegobarrioh.akdemia.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SecurityUtils {


    public static boolean authenticationHasRole(Authentication authentication, String role) {
        if (authentication != null)
            return authoritiesContainsRole((Collection<GrantedAuthority>) authentication.getAuthorities(), role);
        return false;
    }

    public static boolean authoritiesContainsRole(Collection<GrantedAuthority> authorities, String role) {
        if (authorities != null) {
            for (GrantedAuthority authority: authorities) {
                if (authority.getAuthority().equals(role))
                    return true;
            }
        }
        return false;
    }
}
