package practice.project.splitwise.service;

import practice.project.splitwise.dto.*;
import practice.project.splitwise.exception.GroupNotFoundException;
import practice.project.splitwise.exception.UserNotFoundException;
import practice.project.splitwise.exception.UserNotMemberOfGroupException;
import practice.project.splitwise.model.Expense;

import java.util.List;

public interface GroupService {
    List<TransactionDTO> settleUpByGroupId(int groupId) throws GroupNotFoundException;

    GroupCreationResponseDTO createGroup(GroupCreationDTO groupData);

    ExpenseResponseDTO addExpense(ExpenseReceivingDTO expenseData) throws UserNotFoundException,
    GroupNotFoundException, UserNotMemberOfGroupException;
}
