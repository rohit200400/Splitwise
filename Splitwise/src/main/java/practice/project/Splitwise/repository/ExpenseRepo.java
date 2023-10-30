package practice.project.splitwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.project.splitwise.model.Expense;

public interface ExpenseRepo extends JpaRepository<Expense, Integer> {
}
