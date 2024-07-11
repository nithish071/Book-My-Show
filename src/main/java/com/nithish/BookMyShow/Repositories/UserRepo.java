package com.nithish.BookMyShow.Repositories;

import com.nithish.BookMyShow.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByEmailId(String emailId);
}
