package practice.project.Splitwise.model;

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
public class ClientGroup extends baseModel {
    private String name;
    private String description;
    private double totalAmountSpent;

    @Enumerated(EnumType.STRING)
    private Currency defaultCurrency;

    @OneToMany
    private List<Expense> expenses;

    @ManyToMany
    @JoinTable(
            name = "client_clientgroup",
            joinColumns = @JoinColumn(name = "clientgroup_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> clients;
}
