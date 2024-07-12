package com.nithish.BookMyShow.Response;

import com.nithish.BookMyShow.Enum.Genre;
import com.nithish.BookMyShow.Enum.Language;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {

    private String movieName;
    private LocalDate releaseDate;
    private Language language;
    private Double duration;
    private Double ratings;
    private Genre genre;
}
