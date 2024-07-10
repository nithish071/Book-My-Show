package com.nithish.BookMyShow.Services;

import com.nithish.BookMyShow.Entity.*;
import com.nithish.BookMyShow.Enum.SeatType;
import com.nithish.BookMyShow.Repositories.*;
import com.nithish.BookMyShow.Requests.BookTicketRequest;
import com.nithish.BookMyShow.Response.TicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private ShowSeatRepo showSeatRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private TheaterRepo theaterRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TicketRepo ticketRepo;

    public String bookTicket(BookTicketRequest bookTicketRequest){
        //1. Find the show Entity
        Movie movie = movieRepo.findMovieByMovieName(bookTicketRequest.getMovieName());
        Theater theater = theaterRepo.findById(bookTicketRequest.getTheaterId()).get();
        Show show = showRepo.findByMovieAndShowDateAndShowTimeAndTheater(movie,bookTicketRequest.getShowDate(),bookTicketRequest.getShowTime(),theater);
        //2. Find the user
        User user = userRepo.findById(bookTicketRequest.getUserId()).get();
        //3. Mark those Seats as Booked and calculate the price
        Integer totalAmount = 0;
        List<ShowSeat> showSeatList = show.getShowSeatList();
        for(ShowSeat showSeat:showSeatList){
            String seatNo = showSeat.getSeatNo();
            if(bookTicketRequest.getRequestedSeats().contains(seatNo)){
                showSeat.setIsBooked(Boolean.TRUE);
                if(showSeat.getSeatType().equals(SeatType.CLASSIC)){
                    totalAmount = totalAmount + 100;
                } else {
                    totalAmount = totalAmount + 150;
                }
            }
        }
        //4. save the tickets to DB and return Ticket
        Ticket ticket = Ticket.builder().showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .movieName(show.getMovie().getMovieName())
                .theaterName(show.getTheater().getTheaterName())
                .totalAmount(totalAmount)
                .show(show)
                .bookedSeats(bookTicketRequest.getRequestedSeats().toString())
                .user(user).build();
        showSeatRepo.saveAll(showSeatList);

        ticket = ticketRepo.save(ticket);
        return ticket.getTicketId();
    }

    public TicketResponse generateTicket(String ticketId){
        Ticket ticket = ticketRepo.findById(ticketId).get();

        // Entity needs to be converted to TicketResponse

        TicketResponse ticketResponse = TicketResponse.builder()
                .bookedSeats(ticket.getBookedSeats())
                .movieName(ticket.getMovieName())
                .theaterName(ticket.getTheaterName())
                .totalAmount(ticket.getTotalAmount())
                .showTime(ticket.getShowTime())
                .showDate(ticket.getShowDate()).build();

        return ticketResponse;
    }
}
