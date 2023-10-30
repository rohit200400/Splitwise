package practice.project.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor // parameterised constructor
@NoArgsConstructor // default constructor
public class TransactionDTO {
    private String fromUserName;
    private String toUserName;
    private double amount;
}