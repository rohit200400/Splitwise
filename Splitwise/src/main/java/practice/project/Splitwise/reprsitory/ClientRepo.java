package practice.project.Splitwise.reprsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.project.Splitwise.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Integer> {
    List<Client> findByMailIn(List<String> emailList);

    Optional<Client> findByMail(String mail);
}
