package com.nithish.BookMyShow.Contoller;

import com.nithish.BookMyShow.Requests.AddUserRequest;
import com.nithish.BookMyShow.Requests.AuthenticationRequest;
import com.nithish.BookMyShow.Requests.RegisterRequest;
import com.nithish.BookMyShow.Response.AuthenticationResponse;
import com.nithish.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("register")
    public String register(
            @RequestBody AddUserRequest request
    ){
        return userService.registerUser(request);
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
