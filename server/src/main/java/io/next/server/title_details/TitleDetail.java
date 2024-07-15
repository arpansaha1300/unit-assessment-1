package io.next.server.title_details;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;

import io.next.server.common.converters.JpaConverterJson;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "original_title", nullable = false)
  private String originalTitle;

  @Column(name = "plot_overview")
  private String plotOverview;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "runtime_minutes")
  private Integer runtimeMinutes;

  @Column(name = "year", nullable = false)
  private Integer year;

  @Column(name = "end_year")
  private Integer endYear;

  @Column(name = "release_date")
  private String releaseDate;

  @Column(name = "imdb_id")
  private String imdbId;

  @Column(name = "user_rating")
  private Double userRating;

  @Column(name = "critic_score")
  private Double criticScore;

  @Column(name = "poster")
  private String poster;

  @Column(name = "backdrop")
  private String backdrop;

  @Column(name = "original_language", nullable = false)
  private String originalLanguage;

  @Column(name = "relevance_percentile", nullable = false)
  private Double relevancePercentile;

  @Column(name = "popularity_percentile", nullable = false)
  private Double popularityPercentile;

  @Column(name = "trailer")
  private String trailer;

  @Column(name = "trailer_thumbnail")
  private String trailerThumbnail;

  @Column(name = "genres", nullable = false)
  @Convert(converter = JpaConverterJson.class)
  private ArrayList<Integer> genres;

  @Column(name = "genre_names", nullable = false)
  @Convert(converter = JpaConverterJson.class)
  private ArrayList<String> genreNames;
}