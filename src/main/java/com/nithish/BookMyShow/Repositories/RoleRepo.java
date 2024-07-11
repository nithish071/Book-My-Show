package com.nithish.BookMyShow.Repositories;

import com.nithish.BookMyShow.Entity.Role;
import com.nithish.BookMyShow.Enum.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    Role findByRole(Roles role);
}
