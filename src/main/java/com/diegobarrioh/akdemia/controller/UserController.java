package com.diegobarrioh.akdemia.controller;

import com.diegobarrioh.akdemia.domain.dto.UserDTO;
import com.diegobarrioh.akdemia.util.JsonResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
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

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponse> register(@Valid final UserDTO userDto, HttpServletRequest request) {
        log.info("hiting /user/register endpoint");
        log.info("userDto:"+userDto.toString());
        return new ResponseEntity<JsonResponse>(new JsonResponse(true,"/",0,"registration success!!"), HttpStatus.OK);
    }

}
