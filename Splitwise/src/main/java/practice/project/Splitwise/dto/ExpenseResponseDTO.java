package practice.project.splitwise.dto;

import lombok.Getter;
import lombok.Setter;
import practice.project.splitwise.model.Currency;
import practice.project.splitwise.model.Settled;

import java.util.List;

@Getter
@Setter
public class ExpenseResponseDTO {
    private String name;
    private String description;
    private Currency currency;
    private Double totalSpending;
    private Settled isGroupSettled;
    private List<ExpenseDTO> expenseDTOList;
}
