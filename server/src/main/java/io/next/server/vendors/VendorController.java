package io.next.server.vendors;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.next.server.movie_vendors.MovieVendor;

@RestController
@RequestMapping("/vendors")
public class VendorController {

  VendorService vendorService;

  public VendorController(VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @GetMapping("/{movieId}")
  public List<MovieVendor> getVendorsByMovieId(@PathVariable("movieId") int movieId) {
    return vendorService.getVendorsByMovieId(movieId);
  }

}
