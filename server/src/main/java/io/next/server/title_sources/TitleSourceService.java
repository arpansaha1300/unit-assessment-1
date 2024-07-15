package io.next.server.title_sources;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class TitleSourceService {
  TitleSourceRepository titleSourceRepository;

  public TitleSourceService(TitleSourceRepository titleSourceRepository) {
    this.titleSourceRepository = titleSourceRepository;
  }

  public List<TitleSource> getTitleSources(long titleId) {
    ArrayList<TitleSource> titleSources = new ArrayList<>();
    titleSourceRepository.findByTitleId(titleId).forEach(titleSources::add);
    return titleSources;
  }
}
