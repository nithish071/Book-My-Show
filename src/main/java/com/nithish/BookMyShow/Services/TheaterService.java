package com.nithish.BookMyShow.Services;

import com.nithish.BookMyShow.Entity.Theater;
import com.nithish.BookMyShow.Entity.TheaterSeat;
import com.nithish.BookMyShow.Enum.SeatType;
import com.nithish.BookMyShow.Repositories.TheaterRepo;
import com.nithish.BookMyShow.Repositories.TheaterSeatRepo;
import com.nithish.BookMyShow.Requests.AddTheaterRequest;
import com.nithish.BookMyShow.Requests.AddTheatreSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepo theaterRepo;

    @Autowired
    private TheaterSeatRepo theaterSeatRepo;

    public String addTheater(AddTheaterRequest theaterRequest){
        Theater theater = Theater.builder().theaterName(theaterRequest.getName())
                .address(theaterRequest.getAddress()).build();
        theater = this.theaterRepo.save(theater);
        return "The theater has been saved to the DB with theaterId: "+theater.getTheaterId();
    }

    public String associateSeats(AddTheatreSeatsRequest theatreSeatsRequest) {

        int theaterId = theatreSeatsRequest.getTheaterId();
        int noOfClassicSeats = theatreSeatsRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = theatreSeatsRequest.getNoOfPremiumSeats();

        // Get the theater Entity from DB
        Theater theater = this.theaterRepo.findById(theaterId).get();

        //  Generate seatNo
        List<TheaterSeat> theaterSeatList = new ArrayList<>();
        int noOfRowsOfClassicSeats = noOfClassicSeats/5;
        int noOfSeatsInLastClassicRow = noOfClassicSeats%5;
        int row;
        for(row = 1; row <= noOfRowsOfClassicSeats; row++){
            for(int j = 1; j <= 5; j++){
                char ch = (char)('A'+j-1);
                String seatNo = row+""+ch;

                TheaterSeat theaterSeat = TheaterSeat.builder().seatNo(seatNo).seatType(SeatType.CLASSIC)
                        .theater(theater)
                        .build();

                theaterSeatList.add(theaterSeat);
            }
        }

        // for LastRow
        for(int j = 1; j <= noOfSeatsInLastClassicRow; j++){
            char ch = (char)('A'+j-1);
            String seatNo = row+""+ch;

            TheaterSeat theaterSeat = TheaterSeat.builder().seatNo(seatNo).seatType(SeatType.CLASSIC)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
        }

        // Logic for Premium
        if(noOfSeatsInLastClassicRow > 0){
            row++;
        }
        int noOfRowsOfPremiumSeats = noOfPremiumSeats/5;
        int noOfSeatsInLastPremiumRow = noOfPremiumSeats%5;
        for(int start = 1; start <= noOfRowsOfPremiumSeats; start++){
            for(int j = 1; j <= 5; j++){
                char ch = (char)('A'+j-1);
                String seatNo = row+""+ch;

                TheaterSeat theaterSeat = TheaterSeat.builder().seatNo(seatNo).seatType(SeatType.PREMIUM)
                        .theater(theater)
                        .build();

                theaterSeatList.add(theaterSeat);
            }
            row++;
        }

        // for LastRow
        for(int j = 1; j <= noOfSeatsInLastPremiumRow; j++){
            char ch = (char)('A'+j-1);
            String seatNo = row+""+ch;

            TheaterSeat theaterSeat = TheaterSeat.builder().seatNo(seatNo).seatType(SeatType.PREMIUM)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
        }

        theater.setTheaterSeatList(theaterSeatList);
        this.theaterRepo.save(theater);

        // Save it to the DB

        this.theaterSeatRepo.saveAll(theaterSeatList);

        return "Theater Seats has been associated";

    }
}
