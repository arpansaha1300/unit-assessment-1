package io.next.server.movies;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.next.server.movie_vendors.MovieVendor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/movies")
public class MovieController {

  MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping()
  public List<Movie> getAll() {
    return movieService.getAll();
  }

  @GetMapping("/{movieId}")
  public Optional<Movie> getMovieById(@PathVariable("movieId") int movieId) {
    return movieService.getMovieById(movieId);
  }

  @GetMapping("/{movieId}/price")
  public Optional<MovieVendor> getPriceByMovieId(@PathVariable("movieId") int movieId) {
    return movieService.getMinPriceByMovieId(movieId);
  }

}
