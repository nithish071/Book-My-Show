package com.nithish.BookMyShow.Entity;

import com.nithish.BookMyShow.Enum.Genre;
import com.nithish.BookMyShow.Enum.Language;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(unique = true)
    private String movieName;

    private LocalDate releaseDate;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    private Double duration;

    private Double ratings;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;
}
