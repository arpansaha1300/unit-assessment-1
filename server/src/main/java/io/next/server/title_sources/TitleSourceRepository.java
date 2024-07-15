package io.next.server.title_sources;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TitleSourceRepository extends CrudRepository<TitleSource, Long> {
  @Query(value = "select * from title_sources s where title_id = ?1;", nativeQuery = true)
  public Iterable<TitleSource> findByTitleId(long titleId);
}
