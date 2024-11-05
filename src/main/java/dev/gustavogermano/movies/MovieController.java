package dev.gustavogermano.movies;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId){
        return new ResponseEntity<Optional<Movie>>(movieService.singleMovie(imdbId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> createNewMovie(@RequestBody MovieDTO movieDTO){
        Movie newMovie = movieService.createNewMovie(
                movieDTO.getImdbId(),
                movieDTO.getTitle(),
                movieDTO.getReleaseDate(),
                movieDTO.getTrailerLink(),
                movieDTO.getPoster(),
                movieDTO.getGenres(),
                movieDTO.getBackdrops(),
                movieDTO.getReviewIds()
        );

        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{imdbId}")
    public ResponseEntity<Movie> editAMovie(@PathVariable String imdbId, @RequestBody MovieDTO movieDTO) {
        Movie editedMovie = movieService.editAMovie(imdbId, movieDTO);

        return new ResponseEntity<>(editedMovie, HttpStatus.OK);
    }

    @DeleteMapping("/{imdbId}")
    public ResponseEntity<String> deleteAMovie(@PathVariable String imdbId){
        String msg = movieService.deleteAMovie(imdbId);

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


}
