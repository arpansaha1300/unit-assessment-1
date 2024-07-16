package io.next.server.movie_vendors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieVendorRepository extends CrudRepository<MovieVendor, Integer> {
  @Query(value = "select min(mv.price) as price from movie_vendor mv where mv.movie_id = :movieId", nativeQuery = true)
  int findMinPriceByMovieId(@Param("movieId") int movieId);
}
