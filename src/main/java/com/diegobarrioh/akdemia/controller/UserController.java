package com.diegobarrioh.akdemia.controller;

import com.diegobarrioh.akdemia.domain.dto.UserDTO;
import com.diegobarrioh.akdemia.domain.entity.User;
import com.diegobarrioh.akdemia.ex.UserAlreadyExistsException;
import com.diegobarrioh.akdemia.service.UserService;
import com.diegobarrioh.akdemia.util.JsonResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> register(@Valid final UserDTO userDto, HttpServletRequest request) {

        log.debug("Registering user account with information: {}", userDto);
        log.debug("recibida llamada con la ip {}",request.getRemoteAddr());

        User registeredUser = null;
        try {
            registeredUser = userService.registerNewUserAccount(userDto);
            //TODO enviar un evento o algo cuando se ha registrado el usuario.

        } catch (UserAlreadyExistsException uaee) {
            log.warn("UserController:" + "UserAlreadyExistException on registration with email: {}!", userDto.getEmail());
            return new ResponseEntity<JsonResponse>(new JsonResponse(false, null, 02, "An account already exists for that email address!"), HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error("UserController:" + "Some Exception!", e);
            return new ResponseEntity<JsonResponse>(new JsonResponse(false, null, 05, "System Error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // If there were no exceptions then the registration was a success!
        return new ResponseEntity<JsonResponse>(new JsonResponse(true,"/",0,"registration success!!"), HttpStatus.OK);
    }
}
