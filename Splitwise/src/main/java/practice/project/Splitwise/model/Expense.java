package practice.project.Splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Expense extends baseModel {
    private double amount;
    private String description;
    @OneToOne
    private Client paidBy;

    @OneToMany
    private List<UsersSplit> amountSplit;

    public Expense(double amount, String description, Client paidBy) {
        this.amount = amount;
        this.description = description;
        this.paidBy = paidBy;
        this.amountSplit = new ArrayList<>();
        amountSplit.add(new UsersSplit(paidBy, amount));
    }


    /**
     * @param users list of users among whom we have to split equally
     * @return a complete expense object
     */
    public Expense splitEqually(List<Client> users) {
        int totalSplits = users.size();
        double eachSplitAmount = this.getAmount() / totalSplits;

        List<UsersSplit> usersSplits = new ArrayList<>();
        for (Client u : users
        ) {
            if (!u.equals(this.paidBy)) {
                usersSplits.add(new UsersSplit(u, -1 * eachSplitAmount));
            }
        }
        this.setAmountSplit(usersSplits);
        return this;
    }
}
