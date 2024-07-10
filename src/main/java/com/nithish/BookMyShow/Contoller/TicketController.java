package com.nithish.BookMyShow.Contoller;

import com.nithish.BookMyShow.Entity.Ticket;
import com.nithish.BookMyShow.Requests.BookTicketRequest;
import com.nithish.BookMyShow.Response.TicketResponse;
import com.nithish.BookMyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("bookTicket")
    public String bookTicket(@RequestBody BookTicketRequest bookTicketRequest){
        return ticketService.bookTicket(bookTicketRequest);
    }

    @GetMapping("viewTicket")
    public TicketResponse viewTicket(@RequestParam("ticketId") String ticketId){
        return ticketService.generateTicket(ticketId);
    }
}
