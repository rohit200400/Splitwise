package practice.project.splitwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.project.splitwise.model.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
}
