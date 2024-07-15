package io.next.server.title_sources;

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
@Table(name = "title_sources")
public class TitleSource {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "source_id", nullable = false)
  private long sourceId;

  @Column(name = "title_id", nullable = false)
  private long titleId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "region", nullable = false)
  private String region;

  @Column(name = "web_url", nullable = false)
  private String webUrl;

  @Column(name = "format")
  private String format;

  @Column(name = "price")
  private Double price; // Use Double for potential null value

  @Column(name = "seasons", nullable = false)
  private Integer seasons;

  @Column(name = "episodes", nullable = false)
  private Integer episodes;
}