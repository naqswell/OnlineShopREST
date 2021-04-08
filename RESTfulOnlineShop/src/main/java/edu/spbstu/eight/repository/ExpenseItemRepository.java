package edu.spbstu.eight.repository;

import edu.spbstu.eight.domain.ExpenseItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseItemRepository extends CrudRepository<ExpenseItem, Long> {

}
