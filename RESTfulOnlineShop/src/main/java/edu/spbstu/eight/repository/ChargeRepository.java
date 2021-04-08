package edu.spbstu.eight.repository;

import edu.spbstu.eight.domain.Charge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeRepository extends CrudRepository<Charge, Long> {
}
