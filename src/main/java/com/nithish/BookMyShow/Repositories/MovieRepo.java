package com.nithish.BookMyShow.Repositories;

import com.nithish.BookMyShow.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie,Integer> {
    Movie findMovieByMovieName(String movieName);
}
