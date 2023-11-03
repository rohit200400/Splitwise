package practice.project.splitwise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.project.splitwise.dto.*;
import practice.project.splitwise.exception.GroupNotFoundException;
import practice.project.splitwise.exception.UserNotFoundException;
import practice.project.splitwise.exception.UserNotMemberOfGroupException;
import practice.project.splitwise.service.GroupService;

import java.util.List;

@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/settleUp/{groupId}/{userId}")
    public ResponseEntity settleUpForGroup(@PathVariable("groupId") int groupId,
                                           @PathVariable("userId") int userId) throws Exception {
        List<TransactionDTO> transactions = groupService.settleUpByGroupId(groupId, userId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/{groupId}/settled")
    public ResponseEntity groupSettled(@RequestBody SettledDTO settled) throws UserNotFoundException,
            UserNotMemberOfGroupException, GroupNotFoundException {
        groupService.groupSettled(settled);
        return ResponseEntity.ok("Yay... your all are settled.");
    }

    @PostMapping(value = "/createGroup")
    public ResponseEntity createGroup(@RequestBody GroupCreationDTO groupData) {
        GroupCreationResponseDTO newGroup = groupService.createGroup(groupData);
        return ResponseEntity.ok(newGroup);
    }

    @PostMapping(value = "/addExpense")
    public ResponseEntity addExpense(@RequestBody ExpenseReceivingDTO expenseData) throws Exception {
        ExpenseResponseDTO updatedGroup = groupService.addExpense(expenseData);
        return ResponseEntity.ok(updatedGroup);
    }

}
