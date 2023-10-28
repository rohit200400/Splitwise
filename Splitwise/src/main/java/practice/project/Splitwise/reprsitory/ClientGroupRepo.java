package practice.project.Splitwise.reprsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.project.Splitwise.model.ClientGroup;

public interface ClientGroupRepo extends JpaRepository<ClientGroup, Integer> {
}
