package com.nithish.BookMyShow.Requests;

import com.nithish.BookMyShow.Entity.Movie;
import com.nithish.BookMyShow.Enum.City;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AddShowRequest {

    private LocalDate showDate;
    private LocalTime showTime;
    private String movieName;
    private City city;
    private Integer theaterId;

}
