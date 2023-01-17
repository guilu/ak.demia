package com.diegobarrioh.akdemia.ex;

import lombok.extern.log4j.Log4j2;

import java.io.Serial;

@Log4j2
public class EntityNotFoundException extends RuntimeException {

    /**
     * The Constant serialVersionUID.
     */
    @Serial
    private static final long serialVersionUID = -1L;

    public EntityNotFoundException(String entity, Long id) {
        super("Could not find "+entity+" with id "+id);
    }

}
