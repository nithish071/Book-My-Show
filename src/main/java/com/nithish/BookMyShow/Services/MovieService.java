package com.nithish.BookMyShow.Services;

import com.nithish.BookMyShow.Entity.Movie;
import com.nithish.BookMyShow.Entity.Show;
import com.nithish.BookMyShow.Entity.Theater;
import com.nithish.BookMyShow.Enum.City;
import com.nithish.BookMyShow.Enum.Genre;
import com.nithish.BookMyShow.Repositories.MovieRepo;
import com.nithish.BookMyShow.Repositories.ShowRepo;
import com.nithish.BookMyShow.Requests.AddMovieRequest;
import com.nithish.BookMyShow.Requests.UpdateMovieRequest;
import com.nithish.BookMyShow.Response.MovieResponse;
import com.nithish.BookMyShow.Response.MovieWithShowDetails;
import com.nithish.BookMyShow.Response.TheaterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public List<TheaterResponse> getTheaters(City city,String movie,LocalDate date){
        Movie movie1 = this.movieRepo.findMovieByMovieName(movie);
        List<Show> shows = this.showRepo.findByCityAndMovieAndShowDate(city,movie1,date);
        Set<Theater> theaters = new HashSet<>();
        for(Show show: shows){
            theaters.add(show.getTheater());
        }
        List<TheaterResponse> theaterResponses = new ArrayList<>();
        for(Theater theater:theaters){
            theaterResponses.add(
                    TheaterResponse.builder()
                            .theaterName(theater.getTheaterName())
                            .address(theater.getAddress())
                            .build()
            );
        }
        return theaterResponses;
    }

    public List<MovieWithShowDetails> getMovies(City city) {
        List<Show> shows = this.showRepo.findByCityAndShowDate(city, LocalDate.now());
        Set<Movie> movies = new HashSet<>();
        Set<LocalTime> showTime = new HashSet<>();
        for(Show show: shows){
            showTime.add(show.getShowTime());
            movies.add(show.getMovie());
        }
        List<MovieWithShowDetails> movieResponses = new ArrayList<>();
        for(Movie movie:movies){
            movieResponses.add(
                    MovieWithShowDetails.builder()
                            .movieName(movie.getMovieName())
                            .releaseDate(movie.getReleaseDate())
                            .language(movie.getLanguage())
                            .duration(movie.getDuration())
                            .ratings(movie.getRatings())
                            .genre(movie.getGenre())
                            .showDate(LocalDate.now())
                            .showTime(showTime)
                            .build()
            );
        }
        return movieResponses;
    }

    public List<MovieResponse> getMovies(Genre genre) {
        List<Movie> movies = this.movieRepo.findMovieByGenre(genre);
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

    public List<MovieResponse> getMovies(Double rating) {
        List<Movie> movies = this.movieRepo.findMovieByRatingsGreaterThanEqual(rating);
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
