package com.diegobarrioh.akdemia.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class UserDTO {

    /**
     * The first name.
     */
    private String firstName;

    /**
     * The last name.
     */
    private String lastName;

    /**
     *  The username
     */
    private String username;

    /**
     * The password.
     */
    private String password;

    /**
     * The matching password.
     */
    private String matchingPassword;

    /**
     * The email.
     */
    private String email;

    /**
     * The role.
     */
    private Integer role;
}