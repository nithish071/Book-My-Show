package com.nithish.BookMyShow.Services;

import com.nithish.BookMyShow.Config.JwtService;
import com.nithish.BookMyShow.Entity.Role;
import com.nithish.BookMyShow.Entity.User;
import com.nithish.BookMyShow.Enum.Roles;
import com.nithish.BookMyShow.Repositories.RoleRepo;
import com.nithish.BookMyShow.Repositories.UserRepo;
import com.nithish.BookMyShow.Requests.AddUserRequest;
import com.nithish.BookMyShow.Requests.AuthenticationRequest;
import com.nithish.BookMyShow.Response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(AddUserRequest userRequest){
        Role role = this.roleRepo.findByRole(Roles.USER);
        User user = User.builder().age(userRequest.getAge())
                .name(userRequest.getName()).emailId(userRequest.getEmailId())
                .password(passwordEncoder.encode(userRequest.getPassword())).roles(new HashSet<>()).mobileNo(userRequest.getMobileNo()).build();
        user.getRoles().add(role);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(userRequest.getEmailId());
        simpleMailMessage.setFrom("springbackend07@gmail.com");
        simpleMailMessage.setSubject("Welcome to Book My Show Application !!");
        String body = "Hi " +userRequest.getName()+"!\n"+
                "Welcome to Book My Show Application, Enjoy WELCOME10 to get 10% off on Tickets.";

        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
        user = userRepo.save(user);

        return "User has been saved to the DB with userId "+user.getUserId();
    }


    public String createAdmin(String emailID){
        Role role = this.roleRepo.findByRole(Roles.ADMIN);
        User user = this.userRepo.findByEmailId(emailID);
        if(user.getRoles().contains(role)){
            return "User Already has admin access";
        }
        user.getRoles().add(role);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(emailID);
        simpleMailMessage.setFrom("springbackend07@gmail.com");
        simpleMailMessage.setSubject("Granted Admin Role");
        String body = "Hi " +user.getName()+"!\n"+
                "Admin Role of BookMyShow Granted to You";

        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
        user = userRepo.save(user);

        return "User has been saved with admin role to the DB with userId "+user.getUserId();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        User user = userRepo.findByEmailId(request.getEmail());
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
