package com.nithish.BookMyShow.Services;

import com.nithish.BookMyShow.Entity.Movie;
import com.nithish.BookMyShow.Repositories.MovieRepo;
import com.nithish.BookMyShow.Requests.AddMovieRequest;
import com.nithish.BookMyShow.Requests.UpdateMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;

    public String addNewMovie(AddMovieRequest movieRequest) {

        Movie movie1 = new Movie();
        movie1.setMovieName(movieRequest.getMovieName());
        movie1.setReleaseDate(movieRequest.getReleaseDate());
        movie1.setLanguage(movieRequest.getLanguage());
        movie1.setDuration(movieRequest.getDuration());
        movie1.setRatings(movieRequest.getDuration());

        Movie movie2 = this.movieRepo.save(movie1);

        return "Movie has been added successfully  with movieID: "+movie2.getMovieId();
    }

    public String updateMovie(UpdateMovieRequest updateMovieRequest){
        // find Movie
        Movie movie = this.movieRepo.findMovieByMovieName(updateMovieRequest.getMovieName());

        // update movie
        movie.setLanguage(updateMovieRequest.getNewlanguage());
        movie.setRatings(updateMovieRequest.getNewRatings());

        // save the movie
        this.movieRepo.save(movie);
        return "Movie has been updated";
    }
}
