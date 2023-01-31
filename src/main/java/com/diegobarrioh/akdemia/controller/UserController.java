package com.diegobarrioh.akdemia.controller;

import com.diegobarrioh.akdemia.domain.dto.UserDTO;
import com.diegobarrioh.akdemia.domain.entity.User;
import com.diegobarrioh.akdemia.ex.UserAlreadyExistsException;
import com.diegobarrioh.akdemia.service.UserService;
import com.diegobarrioh.akdemia.util.JsonResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
            log.info("se enviaria evento de grabado user {}",registeredUser.getEmail());

        } catch (UserAlreadyExistsException uaee) {
            log.warn("UserController:" + "UserAlreadyExistException on registration with email: {}!", userDto.getEmail());
            throw new UserAlreadyExistsException("An account already exists for that email address!");
        } catch (Exception e) {
            log.error("UserController:" + "Some Exception!", e);
            return new ResponseEntity<>(new JsonResponse(false, "/", 5, "System Error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // If there were no exceptions then the registration was a success!
        return new ResponseEntity<>(new JsonResponse(true,"/register-complete",0,"registration success!!"), HttpStatus.OK);
    }


}
