package io.next.server.movies;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import io.next.server.movie_vendors.MovieVendorRepository;

@Service
public class MovieService {

  MovieRepository movieRepository;
  MovieVendorRepository movieVendorRepository;

  public MovieService(MovieRepository movieRepository, MovieVendorRepository movieVendorRepository) {
    this.movieRepository = movieRepository;
    this.movieVendorRepository = movieVendorRepository;
  }

  public List<Movie> getAll() {
    ArrayList<Movie> movies = new ArrayList<>();
    movieRepository.findAll().forEach(movies::add);
    return movies;
  }

  public Optional<Movie> getMovieById(int movieId) {
    return movieRepository.findById(movieId);
  }

  public Optional<Integer> getMinPriceByMovieId(int movieId) {
    final Optional<Movie> optionalMovie = movieRepository.findById(movieId);

    if (optionalMovie.isEmpty()) {
      return Optional.empty();
    }

    final Movie movie = optionalMovie.get();
    final int price = movieVendorRepository.findMinPriceByMovieId(movieId);

    final int profitMargin = price * movie.getRating() / 10;
    final int finalPrice = price + profitMargin;

    return Optional.of(finalPrice);
  }
}
