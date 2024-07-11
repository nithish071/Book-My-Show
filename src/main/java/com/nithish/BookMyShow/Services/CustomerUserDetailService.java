package com.nithish.BookMyShow.Services;

import com.nithish.BookMyShow.Entity.User;
import com.nithish.BookMyShow.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByEmailId(username);
        if(user == null){
            throw  new UsernameNotFoundException("User not found");
        }
        return user;
    }

}
