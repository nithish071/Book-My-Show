package com.nithish.BookMyShow.Requests;

import com.nithish.BookMyShow.Enum.Language;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddMovieRequest {


    private String movieName;
    private LocalDate releaseDate;
    private Language language;
    private Double duration;
    private Double ratings;
}
