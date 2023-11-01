package practice.project.splitwise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersGroup extends BaseModel {
    private String name;
    private String description;
    private double totalAmountSpent;
    @Enumerated(EnumType.STRING)
    private Settled isSettled;
    @Enumerated(EnumType.STRING)
    private Currency defaultCurrency;

    @OneToMany
    private List<Expense> expenses;

    @ManyToMany
    @JoinTable(
            name = "user_group_mapping",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Users> users;
}
