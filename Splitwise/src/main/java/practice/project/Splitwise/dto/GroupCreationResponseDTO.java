package practice.project.splitwise.dto;

import lombok.Getter;
import lombok.Setter;
import practice.project.splitwise.model.Currency;

import java.util.List;

@Setter
@Getter
public class GroupCreationResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private Currency currency;
    private Double totalSpending;
    private List<UserResponseDTO> usersList;
}
