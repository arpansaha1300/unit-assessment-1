package io.next.server.movie_vendors;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.next.server.movies.Movie;
import io.next.server.vendors.Vendor;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movie_vendors")
public class MovieVendor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "movie_id", nullable = false)
  @JsonBackReference
  private Movie movie;

  @ManyToOne
  @JoinColumn(name = "vendor_id", nullable = false)
  @JsonBackReference
  private Vendor vendor;

  @Column(name = "price", nullable = false)
  private double price;
}
