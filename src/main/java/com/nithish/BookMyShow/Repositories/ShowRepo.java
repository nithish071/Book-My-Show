package com.nithish.BookMyShow.Repositories;

import com.nithish.BookMyShow.Entity.Movie;
import com.nithish.BookMyShow.Entity.Show;
import com.nithish.BookMyShow.Entity.Theater;
import com.nithish.BookMyShow.Enum.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowRepo extends JpaRepository<Show,Integer> {
    Show findByMovieAndShowDateAndShowTimeAndTheater(Movie movie,LocalDate showDate,LocalTime showTime,Theater theater);
    List<Show> findByCityAndShowDate(City city, LocalDate showDate);
}
