package practice.project.splitwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.project.splitwise.model.UsersSplit;

public interface UsersSplitRepo extends JpaRepository<UsersSplit, Integer> {
}
