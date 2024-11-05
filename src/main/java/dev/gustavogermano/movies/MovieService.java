package dev.gustavogermano.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public Movie editAMovie(String imdbId, MovieDTO movieDTO){
        Optional <Movie> op = movieRepository.findMovieByImdbId(imdbId);

        if(op.isPresent()){
            Movie movie = op.get();
            movie.setTitle(movieDTO.getTitle());
            movie.setReleaseDate(movieDTO.getReleaseDate());
            movie.setTrailerLink(movieDTO.getTrailerLink());
            movie.setPoster(movieDTO.getPoster());
            movie.setGenres(movieDTO.getGenres());
            movie.setBackdrops(movieDTO.getBackdrops());
            movie.setReviewIds(movieDTO.getReviewIds());

            return movieRepository.save(movie);
        } else {
            throw new RuntimeException("Movie not found");
        }

    }

    public String deleteAMovie(String imdbId){

        Optional <Movie> op = movieRepository.findMovieByImdbId(imdbId);

        if(op.isPresent()){
            movieRepository.deleteMovieByImdbId(imdbId);
            return "Movie with imdbId" + imdbId + "was successfully deleted";
        } else {
            return "Movie with imdbId" + imdbId + "was not found!";
        }

    }
}
