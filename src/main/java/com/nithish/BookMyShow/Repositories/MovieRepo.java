package com.nithish.BookMyShow.Repositories;

import com.nithish.BookMyShow.Entity.Movie;
import com.nithish.BookMyShow.Enum.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie,Integer> {
    Movie findMovieByMovieName(String movieName);
    List<Movie> findMovieByGenre(Genre genre);
    List<Movie> findMovieByRatingsGreaterThanEqual(Double rating);
}
