package com.nithish.BookMyShow.Response;

import com.nithish.BookMyShow.Enum.Genre;
import com.nithish.BookMyShow.Enum.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieWithShowDetails {
    private String movieName;
    private LocalDate releaseDate;
    private Language language;
    private Double duration;
    private Double ratings;
    private Genre genre;
    private LocalDate showDate;
    private Set<LocalTime> showTime;
}
