package edu.spbstu.eight.repository;

import edu.spbstu.eight.domain.Warehouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository  extends CrudRepository<Warehouse, Long> {

}
