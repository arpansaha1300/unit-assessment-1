package io.next.server.vendors;

import java.util.List;

import org.springframework.stereotype.Service;

import io.next.server.movie_vendors.MovieVendor;
import io.next.server.movie_vendors.MovieVendorRepository;

@Service
public class VendorService {

  VendorRepository vendorRepository;
  MovieVendorRepository movieVendorRepository;

  public VendorService(VendorRepository vendorRepository, MovieVendorRepository movieVendorRepository) {
    this.vendorRepository = vendorRepository;
    this.movieVendorRepository = movieVendorRepository;
  }

  public List<MovieVendor> getVendorsByMovieId(int movieId) {
    return movieVendorRepository.findByMovieIdOrderByPrice(movieId);
  }
}
