package practice.project.splitwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.project.splitwise.model.Users;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByMail(String mail);
}
