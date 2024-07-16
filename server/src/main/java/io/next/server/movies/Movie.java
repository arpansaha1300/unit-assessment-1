package io.next.server.movies;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import io.next.server.posters.Poster;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "plot", nullable = false)
  private String plot;

  @Column(name = "trailer", nullable = false)
  private String trailer;

  @Column(name = "rating", nullable = false)
  private int rating;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "movie")
  @JsonManagedReference
  private List<Poster> posters;

}