package io.next.server.title_sources;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/title")
public class TitleSourceController {
  TitleSourceService titleDetailService;

  public TitleSourceController(TitleSourceService titleDetailService) {
    this.titleDetailService = titleDetailService;
  }

  @GetMapping("/{titleId}/sources")
  public List<TitleSource> getTitleSources(@PathVariable("titleId") long titleId) {
    return titleDetailService.getTitleSources(titleId);
  }

}
