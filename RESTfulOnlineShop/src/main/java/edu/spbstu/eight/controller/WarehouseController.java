package edu.spbstu.eight.controller;

import edu.spbstu.eight.domain.Warehouse;
import edu.spbstu.eight.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
@RequestMapping("warehouse")
public class WarehouseController {
  private final WarehouseRepository warehouseRepository;

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<Warehouse> getAll() {
    return StreamSupport
        .stream(warehouseRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @GetMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Warehouse getOne(@PathVariable("id") Warehouse warehouse) {
    return warehouse;
  }

  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public Warehouse saveSale(@RequestBody Warehouse warehouse) {
    return warehouseRepository.save(warehouse);
  }

  @PutMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public Warehouse updateWarehouse(@PathVariable("id") Warehouse warehouse, @RequestBody Warehouse updatedWarehouse) {
    BeanUtils.copyProperties(updatedWarehouse, warehouse, "id");

    return warehouseRepository.save(warehouse);
  }

  @DeleteMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteWarehouse(@PathVariable("id") Warehouse warehouse) {
    warehouseRepository.delete(warehouse);
  }
}
