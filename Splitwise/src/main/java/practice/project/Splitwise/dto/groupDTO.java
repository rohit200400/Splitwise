package practice.project.Splitwise.dto;

import lombok.Getter;
import practice.project.Splitwise.model.Client;
import practice.project.Splitwise.model.Currency;

import java.util.List;

@Getter
public class groupDTO {
    String name;
    String description;
    Currency currency;
    List<Client> users;
}
