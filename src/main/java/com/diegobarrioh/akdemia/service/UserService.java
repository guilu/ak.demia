package com.diegobarrioh.akdemia.service;

import com.diegobarrioh.akdemia.domain.dto.UserDTO;
import com.diegobarrioh.akdemia.domain.entity.User;
import com.diegobarrioh.akdemia.domain.repository.RoleRepository;
import com.diegobarrioh.akdemia.domain.repository.UserRepository;
import com.diegobarrioh.akdemia.ex.UserAlreadyExistsException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Log4j2
@Service
@Transactional
public class UserService {

    private static final String USER_ROLE_NAME = "ROLE_USER";

    /**
     * The user repository.
     */
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder encoder) {
        this.passwordEncoder = encoder;
    }

    @Value("${user.registration.sendVerificationEmail:false}")
    private boolean sendRegistrationVerificationEmail;

    /**
     * Register new user account.
     *
     * @param newUserDto the new user dto
     * @return the user
     */
    public User registerNewUserAccount(final UserDTO newUserDto) {
        log.debug("UserService.registerNewUserAccount: called with userDto: {}", newUserDto);

        if (emailExists(newUserDto.getEmail())) {
            log.debug("UserService.registerNewUserAccount:" + "email already exists: {}", newUserDto.getEmail());
            throw new UserAlreadyExistsException("There is an account with that email address: " + newUserDto.getEmail());
        }

        // Create a new User entity
        final User user = new User();
        user.setFirstName(newUserDto.getFirstName());
        user.setLastName(newUserDto.getLastName());
        user.setPassword(passwordEncoder.encode(newUserDto.getPassword()));
        user.setEmail(newUserDto.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findByName(USER_ROLE_NAME)));

        // If we are not sending a verification email
        if (!sendRegistrationVerificationEmail) {
            // Enable the user immediately
            user.setEnabled(true);
        }
        return userRepository.save(user);
    }

    /**
     * Email exists.
     *
     * @param email the email
     * @return true, if successful
     */
    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

}
