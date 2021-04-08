package edu.spbstu.eight.controller;

import edu.spbstu.eight.domain.Sale;
import edu.spbstu.eight.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("sale")
@RequiredArgsConstructor
public class SaleController {
  private final SaleRepository saleRepository;

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<Sale> getAll() {
    return StreamSupport
        .stream(saleRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @GetMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Sale getSale(@PathVariable("id") Sale sale) {
    return sale;
  }

  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public Sale saveSale(@RequestBody Sale sale) {
    return saleRepository.save(sale);
  }

  @PutMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Sale updateSale(@PathVariable("id") Sale sale, @RequestBody Sale updatedSale) {
    BeanUtils.copyProperties(updatedSale, sale, "id");

    return saleRepository.save(sale);
  }

  @DeleteMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteSale(@PathVariable("id") Sale sale) {
    saleRepository.delete(sale);
  }
}
