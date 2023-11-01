package practice.project.splitwise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExpenseReceivingDTO {
    private Integer groupID;
    private double amount;
    private String description;
    private Integer paidByUserID;
    private List<UserSplitReceivingDTO> userSplit;
}
