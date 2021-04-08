package edu.spbstu.eight.domain;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long quantity;
  private Long amount;
  private LocalDate saleDate;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Warehouse warehouse;
}
