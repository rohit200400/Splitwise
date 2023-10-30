package practice.project.splitwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.project.splitwise.model.UsersGroup;

public interface GroupRepo extends JpaRepository<UsersGroup, Integer> {

}
