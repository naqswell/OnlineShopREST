package edu.spbstu.eight.controller;

import edu.spbstu.eight.domain.Charge;
import edu.spbstu.eight.repository.ChargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("charge")
@RequiredArgsConstructor
public class ChargeController {
  private final ChargeRepository chargeRepository;

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<Charge> getAll() {
    return StreamSupport
        .stream(chargeRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @GetMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Charge getOne(@PathVariable("id") Charge charge) {
    return charge;
  }

  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public Charge saveCharge(@RequestBody Charge charge) {
    return chargeRepository.save(charge);
  }

  @PutMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Charge updateCharge(@PathVariable("id") Charge charge, @RequestBody Charge updatedCharge) {
    BeanUtils.copyProperties(updatedCharge, charge, "id");

    return chargeRepository.save(charge);
  }

  @DeleteMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteCharge(@PathVariable("id") Charge charge) {
    chargeRepository.delete(charge);
  }
}
