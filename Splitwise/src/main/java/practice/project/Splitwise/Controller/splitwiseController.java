package practice.project.Splitwise.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.project.Splitwise.dto.UserMappingDTO;
import practice.project.Splitwise.dto.groupDTO;
import practice.project.Splitwise.model.Client;
import practice.project.Splitwise.model.ClientGroup;
import practice.project.Splitwise.service.splitwiseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/create")
public class splitwiseController {
    @Autowired
    private splitwiseService service;

    @GetMapping("/getGroup/{groupId}")
    public ResponseEntity<ClientGroup> getGroupById(@PathVariable Integer groupId) {
        Optional<ClientGroup> group = service.getGroupById(groupId);

        if (group.isPresent()) {
            return new ResponseEntity<>(group.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<List<Client>> getUsersByMail(@RequestParam List<String> userMail) {
        List<Client> users = service.getUsersByMail(userMail);
        if (users.size() == userMail.size()) {
            return new ResponseEntity<>(users, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<Client>> getAllUsers() {
        List<Client> users = service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @PostMapping("/createUser")
    public ResponseEntity<List<Client>> createUsers(@RequestBody List<Client> users) {
        List<Client> createdUsers = service.createClient(users);
        return new ResponseEntity<>(createdUsers, HttpStatus.CREATED);
    }

    @PostMapping("/createGroup")
    public ResponseEntity<ClientGroup> createGroup(@RequestBody groupDTO groupDetails) {
        ClientGroup createdGroup = service.createGroup(groupDetails);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @PostMapping("/addMembers")
    public ResponseEntity<ClientGroup> addGroupMembers(@RequestBody UserMappingDTO userMappingDTO) {
        ClientGroup createdGroup = service.addGroupMembers(userMappingDTO.getGrpId(), userMappingDTO.getUserIds());
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }
}
