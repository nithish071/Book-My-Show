package com.nithish.BookMyShow.Contoller;

import com.nithish.BookMyShow.Entity.Movie;
import com.nithish.BookMyShow.Requests.AddMovieRequest;
import com.nithish.BookMyShow.Requests.UpdateMovieRequest;
import com.nithish.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

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
