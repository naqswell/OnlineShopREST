package edu.spbstu.eight.repository;

import edu.spbstu.eight.domain.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {

}
