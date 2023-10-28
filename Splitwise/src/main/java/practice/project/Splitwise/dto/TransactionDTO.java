package practice.project.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;
import practice.project.Splitwise.model.Client;

@Getter
@Setter
public class TransactionDTO {
    Client sender;
    Client receiver;
    Double amount;
}
