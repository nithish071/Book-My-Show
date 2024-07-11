package com.nithish.BookMyShow.Bootstrap;

import com.nithish.BookMyShow.Entity.Role;
import com.nithish.BookMyShow.Entity.User;
import com.nithish.BookMyShow.Enum.Roles;
import com.nithish.BookMyShow.Repositories.RoleRepo;
import com.nithish.BookMyShow.Repositories.UserRepo;
import com.nithish.BookMyShow.Requests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class BootStrapApp implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleRepo roleRepo;



    @Autowired
    private UserRepo userRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadRoles();
        this.createSuperAdministrator();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private void loadRoles() {
        Roles[] roles = {Roles.ADMIN,Roles.USER,Roles.SUPER_ADMIN};
        for(Roles role:roles){
            Role findRole = this.roleRepo.findByRole(role);
            if(findRole == null){
                roleRepo.save(Role.builder().role(role).build());
            }
        }
    }

    private void createSuperAdministrator() {

        AddUserRequest userRequest = new AddUserRequest();
        userRequest.setName("Super Admin");
        userRequest.setEmailId("admin124@gmail.com");
        userRequest.setPassword("123456");

        Role role = roleRepo.findByRole(Roles.SUPER_ADMIN);
        User adminuser = userRepo.findByEmailId(userRequest.getEmailId());

        if(role == null || adminuser != null){
            return;
        }

        User user = User.builder().name(userRequest.getName())
                .emailId(userRequest.getEmailId())
                .password(passwordEncoder().encode(userRequest.getPassword())).roles(new HashSet<>()).build();
        user.getRoles().add(role);
        userRepo.save(user);
    }
}
