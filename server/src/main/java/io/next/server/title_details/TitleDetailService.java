package io.next.server.title_details;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TitleDetailService {
  TitleDetailRepository titleDetailRepository;

  public TitleDetailService(TitleDetailRepository titleDetailRepository) {
    this.titleDetailRepository = titleDetailRepository;
  }

  public TitleDetail getTitleDetail(Long titleId) {
    return titleDetailRepository.findById(titleId).get();
  }
}
