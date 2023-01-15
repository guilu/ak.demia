package com.diegobarrioh.akdemia.ex;

import lombok.extern.log4j.Log4j2;

import java.io.Serial;

@Log4j2
public class EntityNotFoundException extends jakarta.persistence.EntityNotFoundException {

    /**
     * The Constant serialVersionUID.
     */
    @Serial
    private static final long serialVersionUID = -1L;


    /**
     * Instantiates a new error accesing entity from repository...
     *
     * @param message the message
     */
    public EntityNotFoundException(final String message, final String repo) {
        //super(message);
        log.error("Entity Not Found exception: {} on repo: {} ",message, repo);
    }

}
