package com.nithish.BookMyShow.Requests;

import lombok.Data;

@Data
public class AddTheatreSeatsRequest {

    private Integer theaterId;
    private Integer noOfClassicSeats;
    private Integer noOfPremiumSeats;

}
