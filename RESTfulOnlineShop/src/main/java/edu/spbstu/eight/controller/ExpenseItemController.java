package edu.spbstu.eight.controller;

import edu.spbstu.eight.domain.ExpenseItem;
import edu.spbstu.eight.repository.ExpenseItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
@RequestMapping("expense_item")
public class ExpenseItemController {
  private final ExpenseItemRepository expenseItemRepository;

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<ExpenseItem> getAll() {
    return StreamSupport
        .stream(expenseItemRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  @GetMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public ExpenseItem getOne(@PathVariable("id") ExpenseItem expenseItem) {
    return expenseItem;
  }

  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public ExpenseItem saveItem(@RequestBody ExpenseItem expenseItem) {
    return expenseItemRepository.save(expenseItem);
  }

  @PutMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public ExpenseItem updateItem(
      @PathVariable("id") ExpenseItem expenseItem,
      @RequestBody ExpenseItem updatedExpenseItem
  ) {
    BeanUtils.copyProperties(updatedExpenseItem, expenseItem, "id");

    return expenseItemRepository.save(expenseItem);
  }

  @DeleteMapping("{id}")
  @PreAuthorize("isAuthenticated()")
  public void deleteItem(@PathVariable("id") ExpenseItem expenseItem) {
    expenseItemRepository.delete(expenseItem);
  }
}
