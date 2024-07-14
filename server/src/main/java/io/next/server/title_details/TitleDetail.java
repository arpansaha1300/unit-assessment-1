package io.next.server.title_details;

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
@Table(name = "title_details")
public class TitleDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "original_title", nullable = false)
  private String originalTitle;

  @Column(name = "plot_overview", nullable = false)
  private String plotOverview;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "runtime_minutes", nullable = false)
  private Integer runtimeMinutes;

  @Column(name = "year", nullable = false)
  private Integer year;

  @Column(name = "end_year")
  private Integer endYear;

  @Column(name = "release_date", nullable = false)
  private String releaseDate;

  @Column(name = "imdb_id", unique = true, nullable = false)
  private String imdbId;

  @Column(name = "user_rating", nullable = false)
  private Double userRating;

  @Column(name = "critic_score")
  private Double criticScore;

  @Column(name = "poster", nullable = false)
  private String poster;

  @Column(name = "backdrop", nullable = false)
  private String backdrop;

  @Column(name = "original_language", nullable = false)
  private String originalLanguage;

  @Column(name = "relevance_percentile", nullable = false)
  private Double relevancePercentile;

  @Column(name = "popularity_percentile", nullable = false)
  private Double popularityPercentile;

  @Column(name = "trailer", nullable = false)
  private String trailer;

  @Column(name = "trailer_thumbnail", nullable = false)
  private String trailerThumbnail;

  @Column(name = "genres", nullable = false)
  private Integer[] genres;

  @Column(name = "genre_names", nullable = false)
  private String[] genreNames;
}