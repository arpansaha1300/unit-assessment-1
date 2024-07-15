package io.next.server.releases;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/releases")
public class ReleaseController {
  ReleaseService releaseService;

  public ReleaseController(ReleaseService releaseService) {
    this.releaseService = releaseService;
  }

  @GetMapping()
  public List<Release> getReleases() {
    return releaseService.getReleases();
  }

}
