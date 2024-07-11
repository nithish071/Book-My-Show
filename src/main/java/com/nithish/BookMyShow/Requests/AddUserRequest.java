package com.nithish.BookMyShow.Requests;

import com.nithish.BookMyShow.Enum.Roles;
import lombok.Data;

import java.util.Set;

@Data
public class AddUserRequest {
    private String name;
    private Integer age;
    private String emailId;
    private String password;
    private String mobileNo;
}
