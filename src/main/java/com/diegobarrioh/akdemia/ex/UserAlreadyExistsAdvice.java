package com.diegobarrioh.akdemia.ex;

import com.diegobarrioh.akdemia.util.JsonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class UserAlreadyExistsAdvice {

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    JsonResponse userAlreadyExists(UserAlreadyExistsException ex) {
        log.error("UserAlreadyExistsAdvice: intercepted");
        return new JsonResponse(false, "/", 2, "An account already exists for that email address!");
    }

}
