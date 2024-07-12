package com.nithish.BookMyShow.Contoller;

import com.nithish.BookMyShow.Repositories.UserRepo;
import com.nithish.BookMyShow.Requests.AddUserRequest;
import com.nithish.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("grant-admin-permission")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String createAdministrator(@RequestParam("email") String emailID) {
        String createdAdmin = userService.createAdmin(emailID);
        return createdAdmin;
    }
}
