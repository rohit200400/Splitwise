package practice.project.splitwise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.project.splitwise.dto.*;
import practice.project.splitwise.exception.GroupNotFoundException;
import practice.project.splitwise.service.GroupService;

import java.util.List;

@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/settleUp/{groupId}")
    public ResponseEntity settleUpForGroup(@PathVariable("groupId") int groupId) throws GroupNotFoundException {
        List<TransactionDTO> transactions = groupService.settleUpByGroupId(groupId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping(value = "/createGroup")
    public ResponseEntity createGroup(@RequestBody GroupCreationDTO groupData) {
        GroupCreationResponseDTO newGroup = groupService.createGroup(groupData);
        return ResponseEntity.ok(newGroup);
    }

    @PostMapping(value = "/addExpense")
    public ResponseEntity addExpense(@RequestBody ExpenseReceivingDTO expenseData) throws Exception{
        ExpenseResponseDTO updatedGroup = groupService.addExpense(expenseData);
        return ResponseEntity.ok(updatedGroup);
    }

}
