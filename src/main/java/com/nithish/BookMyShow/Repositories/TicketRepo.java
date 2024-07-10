package com.nithish.BookMyShow.Repositories;

import com.nithish.BookMyShow.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket,String> {
}
