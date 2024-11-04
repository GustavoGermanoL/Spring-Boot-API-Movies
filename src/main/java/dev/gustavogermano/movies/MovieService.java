package dev.gustavogermano.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }
    public Optional<Movie> singleMovie(String imdbId){
        return movieRepository.findMovieByImdbId(imdbId);
    }

    public Movie createNewMovie(String imdbId, String title, String releaseDate, String trailerLink, String poster, List<String> genres, List<String> backdrops, List<Review> reviewIds){
        Movie newMovie = movieRepository.insert(new Movie(imdbId, title, releaseDate, trailerLink, poster, genres,  backdrops, reviewIds));

        return newMovie;
    }

    public Movie editAMovie(String imdbId, String title, String releaseDate, String trailerLink, String poster, List<String> genres, List<String> backdrops, List<Review> reviewIds){

        Movie editedMovie = movieRepository.save();

        return editedMovie;
    }
}
