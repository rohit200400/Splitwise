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
@AllArgsConstructor
@NoArgsConstructor
public class Client extends baseModel {
    private String name;

    @Column(unique = true)
    private String mail;

    @ManyToMany
    @JoinTable(
            name = "client_clientgroup",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "clientgroup_id")
    )
    private List<ClientGroup> clientGroups;
}
