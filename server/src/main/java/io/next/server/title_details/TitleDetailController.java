package io.next.server.title_details;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/title")
public class TitleDetailController {
  TitleDetailService titleDetailService;

  public TitleDetailController(TitleDetailService titleDetailService) {
    this.titleDetailService = titleDetailService;
  }

  @GetMapping("/{titleId}/details")
  public TitleDetail getTitleDetail(@PathVariable("titleId") Long titleId) {
    return titleDetailService.getTitleDetail(titleId);
  }

}
