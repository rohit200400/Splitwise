package practice.project.splitwise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Users extends BaseModel {

    private String name;
    private String mail;

    @ManyToMany
    @JoinTable(
            name = "user_group_mapping",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<UsersGroup> usersGroups;
}
