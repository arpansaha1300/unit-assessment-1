package io.next.server.movies;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
  List<Movie> findByTitleContaining(String title);
}
