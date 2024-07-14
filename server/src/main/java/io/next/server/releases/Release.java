package io.next.server.releases;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "releases")
public class Release {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "imdb_id", nullable = false, unique = true)
  private String imdbId;

  @Column(name = "season_number", nullable = false)
  private Integer seasonNumber;

  @Column(name = "poster_url")
  private String posterUrl;

  @Column(name = "source_release_date", nullable = false)
  private String sourceReleaseDate;

  @Column(name = "source_id", nullable = false)
  private Integer sourceId;

  @Column(name = "source_name", nullable = false)
  private String sourceName;

  @Column(name = "is_original", nullable = false)
  private Integer isOriginal;
}
