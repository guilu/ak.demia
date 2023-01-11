package com.diegobarrioh.akdemia.service;

import com.diegobarrioh.akdemia.domain.dto.UserDetailsDTO;
import com.diegobarrioh.akdemia.domain.entity.Privilege;
import com.diegobarrioh.akdemia.domain.entity.Role;
import com.diegobarrioh.akdemia.domain.entity.User;
import com.diegobarrioh.akdemia.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Log4j2
@Transactional
@Service
public class UserDetailsServiceCustom implements UserDetailsService {

    protected UserRepository userRepository;

    public UserDetailsServiceCustom(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        log.debug("intento cargar el usuario con el mail {}", email);

        final User user = userRepository.findByEmail(email);

        if (user == null) {
            log.error("No user found with email/username: {}", email);
            throw new UsernameNotFoundException("No user found with email/username:" + email);
        }

        try {
            // Updating lastActivity date for this login
            user.setLastActivityDate(new Date());
            userRepository.save(user);
            return new UserDetailsDTO(user, getAuthorities(user.getRoles()));

        } catch (final Exception e) {
            log.error("DSUserDetailsService.loadUserByUsername:" + "Exception!", e);
            throw new RuntimeException(e);
        }

    }

    /**
     * Gets the authorities.
     *
     * @param roles the roles
     * @return the authorities
     */
    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    /**
     * Gets the privileges.
     *
     * @param roles the roles
     * @return the privileges
     */
    private List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new ArrayList<>();
        final List<Privilege> collection = new ArrayList<>();
        for (final Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (final Privilege item : collection) {
            privileges.add(item.getName());
        }

        return privileges;
    }

    /**
     * Gets the granted authorities.
     *
     * @param privileges the privileges
     * @return the granted authorities
     */
    private List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}
