package com.diegobarrioh.akdemia.domain.dto;

import com.diegobarrioh.akdemia.domain.entity.User;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;

@ToString
public class UserDetailsDTO implements UserDetails {

    /**
     * The Constant serialVersionUID.
     */
    @Serial
    private static final long serialVersionUID = 5286810064622508389L;

    /**
     * The user.
     */
    private final User user;

    /**
     * The granted authorities.
     */
    private final Collection<? extends GrantedAuthority> grantedAuthorities;


    public UserDetailsDTO(User user) {
        this(user, new ArrayList<>());
    }

    public UserDetailsDTO(User user, Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.user = user;
        this.grantedAuthorities = grantedAuthorities;
    }

    /**
     * Gets the authorities.
     *
     * @return the authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

}
