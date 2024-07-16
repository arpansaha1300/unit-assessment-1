package io.next.server.posters;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PosterService {

  PosterRepository posterRepository;

  public PosterService(PosterRepository posterRepository) {
    this.posterRepository = posterRepository;
  }

  public List<Poster> getPostersByMovieId(int movieId) {
    return posterRepository.findByMovieId(movieId);
  }
}
