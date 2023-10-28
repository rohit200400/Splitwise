package practice.project.Splitwise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.project.Splitwise.dto.groupDTO;
import practice.project.Splitwise.model.Client;
import practice.project.Splitwise.model.ClientGroup;
import practice.project.Splitwise.reprsitory.ClientGroupRepo;
import practice.project.Splitwise.reprsitory.ClientRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class splitwiseService {
    @Autowired
    private ClientRepo clientRepo;
    @Autowired
    private ClientGroupRepo groupRepo;

    public List<Client> createClient(List<Client> users) {
        List<Client> savedUsers = clientRepo.saveAll(users);
        return savedUsers;
    }

    public ClientGroup createGroup(groupDTO group) {
        List<Client> savedUsers = new ArrayList<>();
        for (Client user : group.getUsers()
        ) {
            Optional<Client> currUser = clientRepo.findByMail(user.getMail());
            if (currUser.isPresent()) {
                currUser.get().setName(user.getName());
                savedUsers.add(clientRepo.save(currUser.get()));
            } else {
                savedUsers.add(clientRepo.save(user));
            }
        }

        ClientGroup newGroup = new ClientGroup();
        newGroup.setName(group.getName());
        newGroup.setDescription(group.getDescription());
        newGroup.setDefaultCurrency(group.getCurrency());
        newGroup.setClients(savedUsers);
        return groupRepo.save(newGroup);
    }

    public ClientGroup addGroupMembers(Integer groupId, List<Integer> UserId) {
        Optional<ClientGroup> group = groupRepo.findById(groupId);
        if (group.isPresent()) {
            for (Integer n : UserId
            ) {
                Optional<Client> user = clientRepo.findById(n);
                if (user.isPresent()) {
                    group.get().getClients().add(user.get());
                }
            }
        }
        if (group.isPresent()) {
            return groupRepo.save(group.get());
        }
        return null;
    }

    public List<Client> getUsersByMail(List<String> userMail) {

        return clientRepo.findByMailIn(userMail);
    }

    public List<Client> getAllUsers() {

        return clientRepo.findAll();
    }

    public Optional<ClientGroup> getGroupById(Integer id) {
        return groupRepo.findById(id);
    }
}
