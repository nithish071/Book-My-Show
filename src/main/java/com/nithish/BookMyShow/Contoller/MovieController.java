package com.nithish.BookMyShow.Contoller;

import com.nithish.BookMyShow.Entity.Movie;
import com.nithish.BookMyShow.Enum.City;
import com.nithish.BookMyShow.Enum.Genre;
import com.nithish.BookMyShow.Requests.AddMovieRequest;
import com.nithish.BookMyShow.Requests.UpdateMovieRequest;
import com.nithish.BookMyShow.Response.MovieResponse;
import com.nithish.BookMyShow.Response.MovieWithShowDetails;
import com.nithish.BookMyShow.Response.TheaterResponse;
import com.nithish.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("in-theaters-today")
    public ResponseEntity<List<MovieWithShowDetails>> getMovies(@RequestParam("city") City city){
        List<MovieWithShowDetails> list = this.movieService.getMovies(city);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("filter-by-genre")
    public ResponseEntity<List<MovieResponse>> getMovies(@RequestParam("genre") Genre genre){
        List<MovieResponse> list = this.movieService.getMovies(genre);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("movies-above-this-rating")
    public ResponseEntity<List<MovieResponse>> getMovies(@RequestParam("rating") Double rating){
        List<MovieResponse> list = this.movieService.getMovies(rating);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("theaters-screening-this-movie")
    public ResponseEntity<List<TheaterResponse>> getMovies(@RequestParam("city") City city, @RequestParam("movie") String movieName, @RequestParam("date")LocalDate date){
        List<TheaterResponse> list = this.movieService.getTheaters(city,movieName,date);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    @PostMapping("addMovie")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity addNewMovie(@RequestBody AddMovieRequest movie){
        String result = this.movieService.addNewMovie(movie);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("updateMovie")
    public ResponseEntity updateMovie(@RequestBody UpdateMovieRequest updateMovieRequest){
        return new ResponseEntity(this.movieService.updateMovie(updateMovieRequest),HttpStatus.OK);
    }
}
