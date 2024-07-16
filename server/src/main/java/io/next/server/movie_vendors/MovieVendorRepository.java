package io.next.server.movie_vendors;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieVendorRepository extends CrudRepository<MovieVendor, Integer> {
  @Query(value = "select min(mv.price) as price from movie_vendor mv where mv.movie_id = :movieId", nativeQuery = true)
  int findMinPriceByMovieId(@Param("movieId") int movieId);

  List<MovieVendor> findByMovieIdOrderByPrice(@Param("movieId") int movieId);

  MovieVendor findFirstByMovieIdOrderByPrice(@Param("movieId") int movieId);
}
