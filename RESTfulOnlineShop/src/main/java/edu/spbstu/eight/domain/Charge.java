package edu.spbstu.eight.domain;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Charge {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long amount;
  private LocalDate chargeDate;

  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  private ExpenseItem expenseItem;
}
