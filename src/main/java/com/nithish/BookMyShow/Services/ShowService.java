package com.nithish.BookMyShow.Services;

import com.nithish.BookMyShow.Entity.*;
import com.nithish.BookMyShow.Repositories.MovieRepo;
import com.nithish.BookMyShow.Repositories.ShowRepo;
import com.nithish.BookMyShow.Repositories.ShowSeatRepo;
import com.nithish.BookMyShow.Repositories.TheaterRepo;
import com.nithish.BookMyShow.Requests.AddShowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private TheaterRepo theaterRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ShowSeatRepo showSeatRepo;

    public String addShow(AddShowRequest showRequest){
        Movie movie = this.movieRepo.findMovieByMovieName(showRequest.getMovieName());
        Theater theater = this.theaterRepo.findById(showRequest.getTheaterId()).get();

        if(movie == null){
            return "Movie Not Found";
        }
        if(theater == null){
            return "Theater Not Found";
        }
        Show show = Show.builder().showDate(showRequest
                .getShowDate())
                .showTime(showRequest.getShowTime())
                .movie(movie)
                .theater(theater)
                .build();
        this.showRepo.save(show);
        // Associate corresponding showSeats
        List<TheaterSeat> theaterSeats = theater.getTheaterSeatList();
        List<ShowSeat> showSeats = new ArrayList<>();
        for(TheaterSeat theaterSeat:theaterSeats){
            ShowSeat showSeat = ShowSeat.builder().seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType())
                    .isFoodAttached(Boolean.FALSE)
                    .isBooked(Boolean.FALSE)
                    .show(show)
                    .build();
            showSeats.add(showSeat);
        }
        // Bidirectional mapping
        show.setShowSeatList(showSeats);
        showSeatRepo.saveAll(showSeats);
        return "The show has been saved to the DB with showId "+show.getShowId();
    }
}
