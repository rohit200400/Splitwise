package practice.project.Splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class UsersSplit extends baseModel {

    @OneToOne
    private Client user;

    private double amount;
}
