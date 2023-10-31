package practice.project.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense extends BaseModel {
    private double amount;
    private String description;
    @OneToOne
    private Users paidBy;

    @OneToMany
    private List<UsersSplit> amountSplit;

    public Expense(double amount, String description, Users paidBy) {
        this.amount = amount;
        this.description = description;
        this.paidBy = paidBy;
    }


    /**
     * @param users list of users among whom we have to split equally
     * @return a complete expense object
     */
    public Expense splitEqually(List<Users> users) {
        int totalSplits = users.size();
        double eachSplitAmount = this.getAmount() / totalSplits;

        List<UsersSplit> usersSplits = new ArrayList<>();
        usersSplits.add(new UsersSplit(this.paidBy, this.amount));
        for (Users u : users
        ) {
            usersSplits.add(new UsersSplit(u, -1 * eachSplitAmount));
        }
        this.setAmountSplit(usersSplits);
        return this;
    }
}
