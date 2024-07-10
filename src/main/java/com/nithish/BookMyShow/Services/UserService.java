package com.nithish.BookMyShow.Services;

import com.nithish.BookMyShow.Entity.User;
import com.nithish.BookMyShow.Repositories.UserRepo;
import com.nithish.BookMyShow.Requests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    public String addUser(AddUserRequest userRequest){
        User user = User.builder().age(userRequest.getAge())
                .name(userRequest.getName()).emailId(userRequest.getEmailId())
                .mobileNo(userRequest.getMobileNo()).build();

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
}
