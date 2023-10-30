package practice.project.splitwise.service.strategy;

import practice.project.splitwise.dto.TransactionDTO;
import practice.project.splitwise.model.Expense;

import java.util.List;

public interface SettleUpStrategy {
    List<TransactionDTO> settleUp(List<Expense> expenses);
}
