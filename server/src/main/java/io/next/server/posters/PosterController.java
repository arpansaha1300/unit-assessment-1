package io.next.server.posters;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posters")
public class PosterController {

  PosterService posterService;

  public PosterController(PosterService posterService) {
    this.posterService = posterService;
  }

  @GetMapping("/{movieId}")
  public List<Poster> getPostersByMovieId(@PathVariable("movieId") int movieId) {
    return posterService.getPostersByMovieId(movieId);
  }

}
