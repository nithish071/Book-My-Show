package com.nithish.BookMyShow.Contoller;

import com.nithish.BookMyShow.Repositories.UserRepo;
import com.nithish.BookMyShow.Requests.AddUserRequest;
import com.nithish.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("addUser")
    public String addUser(@RequestBody AddUserRequest userRequest){
        return this.userService.addUser(userRequest);
    }
}
