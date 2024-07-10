package com.nithish.BookMyShow.Requests;

import com.nithish.BookMyShow.Enum.Language;
import lombok.Data;

@Data
public class UpdateMovieRequest {

    private String movieName;
    private Language newlanguage;
    private Double newRatings;

}
