package io.next.server.movies;

import java.util.List;
import java.util.Optional;

import io.next.server.movies.MovieService.MovieWithPrice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

  MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping()
  public List<MovieWithPrice> getAll(@RequestParam(required = false) String search) {
    if (search != null) {
      return movieService.getAllByQuery(search);
    }

    return movieService.getAll();
  }

  @GetMapping("/{movieId}")
  public Optional<MovieWithPrice> getMovieById(@PathVariable("movieId") int movieId) {
    return movieService.getMovieById(movieId);
  }

}
