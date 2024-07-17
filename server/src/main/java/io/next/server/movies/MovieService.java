package io.next.server.movies;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import io.next.server.movie_vendors.MovieVendor;
import io.next.server.movie_vendors.MovieVendorRepository;
import lombok.Getter;
import lombok.Setter;

@Service
public class MovieService {

  MovieRepository movieRepository;
  MovieVendorRepository movieVendorRepository;

  @Getter
  @Setter
  public class MovieWithPrice extends Movie {
    private double price;

    public MovieWithPrice(Movie movie, double price) {
      super(movie.getId(), movie.getTitle(), movie.getPlot(), movie.getTrailer(), movie.getRating(),
          movie.getPosters());
      this.price = price;
    }
  }

  public MovieService(MovieRepository movieRepository, MovieVendorRepository movieVendorRepository) {
    this.movieRepository = movieRepository;
    this.movieVendorRepository = movieVendorRepository;
  }

  public List<MovieWithPrice> getAll() {
    ArrayList<MovieWithPrice> moviesWithPrice = new ArrayList<>();

    movieRepository.findAll().forEach(movie -> {
      final double price = getMinPriceByMovie(movie);
      moviesWithPrice.add(new MovieWithPrice(movie, price));
    });

    return moviesWithPrice;
  }

  public List<MovieWithPrice> getAllByQuery(String search) {
    ArrayList<MovieWithPrice> moviesWithPrice = new ArrayList<>();

    movieRepository.findByTitleContaining(search).forEach(movie -> {
      final double price = getMinPriceByMovie(movie);
      moviesWithPrice.add(new MovieWithPrice(movie, price));
    });

    return moviesWithPrice;
  }

  public Optional<MovieWithPrice> getMovieById(int movieId) {
    Optional<Movie> optionalMovie = movieRepository.findById(movieId);

    if (optionalMovie.isEmpty()) {
      return Optional.empty();
    }

    Movie movie = optionalMovie.get();
    final double price = getMinPriceByMovie(movie);

    MovieWithPrice movieWithPrice = new MovieWithPrice(movie, price);

    return Optional.of(movieWithPrice);
  }

  private Double getMinPriceByMovie(Movie movie) {
    final MovieVendor movieVendor = movieVendorRepository.findFirstByMovieIdOrderByPrice(movie.getId());

    final double profitMargin = movieVendor.getPrice() * movie.getRating() / 10;
    return movieVendor.getPrice() + profitMargin;
  }
}
