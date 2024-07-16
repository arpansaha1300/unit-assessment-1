package io.next.server.posters;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PosterRepository extends CrudRepository<Poster, Integer> {
  @Query(value = "select * from poster p where p.movie_id = :movieId", nativeQuery = true)
  List<Poster> findByMovieId(@Param("movieId") int movieId);
}
