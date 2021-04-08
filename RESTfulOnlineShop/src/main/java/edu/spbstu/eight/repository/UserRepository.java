package edu.spbstu.eight.repository;

import edu.spbstu.eight.auth.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String name);
}
