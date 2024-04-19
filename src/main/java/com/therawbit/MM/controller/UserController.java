package com.therawbit.MM.controller;

import com.therawbit.MM.dto.ApiResponse;
import com.therawbit.MM.dto.UserDTO;
import com.therawbit.MM.dto.UserRegisterDTO;
import com.therawbit.MM.service.UserService;
import com.therawbit.MM.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService service;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public UserDTO addUser(@Payload UserDTO user){
        service.updateStatus(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public User disconnect(@Payload User user){
        service.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getConnectedUsers(){
        return ResponseEntity.ok(service.findConnectedUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserRegisterDTO registerDto, Errors errors) {

        if (!errors.getAllErrors().isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(errors.getAllErrors().get(0).getDefaultMessage(), false), HttpStatus.BAD_REQUEST);
        }
        log.info(registerDto.toString());


        service.registerUser(registerDto);
        return new ResponseEntity<>(new ApiResponse("User created successfully.", true), HttpStatus.OK);


    }
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserDetails(){
        return new ResponseEntity<>(service.getUserDetail(),HttpStatus.OK);
    }

}
