package io.next.server.releases;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReleaseService {
  ReleaseRepository releaseRepository;

  public ReleaseService(ReleaseRepository releaseRepository) {
    this.releaseRepository = releaseRepository;
  }

  public List<Release> getReleases() {
    ArrayList<Release> releases = new ArrayList<>();
    releaseRepository.findAll().forEach(releases::add);
    return releases;
  }
}
