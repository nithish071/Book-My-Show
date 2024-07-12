package com.nithish.BookMyShow.Services;

import com.nithish.BookMyShow.Entity.Movie;
import com.nithish.BookMyShow.Entity.Show;
import com.nithish.BookMyShow.Enum.City;
import com.nithish.BookMyShow.Repositories.MovieRepo;
import com.nithish.BookMyShow.Repositories.ShowRepo;
import com.nithish.BookMyShow.Requests.AddMovieRequest;
import com.nithish.BookMyShow.Requests.UpdateMovieRequest;
import com.nithish.BookMyShow.Response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ShowRepo showRepo;

    public String addNewMovie(AddMovieRequest movieRequest) {

        Movie movie1 = new Movie();
        movie1.setMovieName(movieRequest.getMovieName());
        movie1.setReleaseDate(movieRequest.getReleaseDate());
        movie1.setLanguage(movieRequest.getLanguage());
        movie1.setDuration(movieRequest.getDuration());
        movie1.setRatings(movieRequest.getRatings());
        movie1.setGenre(movieRequest.getGenre());

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

    public List<MovieResponse> getMovies(City city) {
        List<Show> shows = this.showRepo.findByCityAndShowDate(city, LocalDate.now());
        Set<Movie> movies = new HashSet<>();
        for(Show show: shows){
            movies.add(show.getMovie());
        }
        List<MovieResponse> movieResponses = new ArrayList<>();
        for(Movie movie:movies){
            movieResponses.add(
                    MovieResponse.builder()
                            .movieName(movie.getMovieName())
                            .releaseDate(movie.getReleaseDate())
                            .language(movie.getLanguage())
                            .duration(movie.getDuration())
                            .ratings(movie.getRatings())
                            .genre(movie.getGenre())
                            .build()
            );
        }
        return movieResponses;
    }
}
