package com.diegobarrioh.akdemia.ex;

import java.io.Serial;

public class UpdatingUserException extends RuntimeException {

    /**
     * The Constant serialVersionUID.
     */
    @Serial
    private static final long serialVersionUID = -2L;

    public UpdatingUserException() {
        super();
    }

    public UpdatingUserException(String message) {
        super(message);

    }
}
