package io.next.server.vendors;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class VendorService {

  VendorRepository vendorRepository;

  public VendorService(VendorRepository vendorRepository) {
    this.vendorRepository = vendorRepository;
  }

  public List<Vendor> getAll() {
    ArrayList<Vendor> vendors = new ArrayList<>();
    vendorRepository.findAll().forEach(vendors::add);
    return vendors;
  }
}
