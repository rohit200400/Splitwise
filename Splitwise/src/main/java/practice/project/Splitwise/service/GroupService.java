package practice.project.splitwise.service;

import practice.project.splitwise.dto.*;
import practice.project.splitwise.exception.GroupNotFoundException;
import practice.project.splitwise.exception.UserNotFoundException;
import practice.project.splitwise.exception.UserNotMemberOfGroupException;

import java.util.List;

public interface GroupService {
    List<TransactionDTO> settleUpByGroupId(int groupId, int userId) throws UserNotFoundException,
            GroupNotFoundException, UserNotMemberOfGroupException;

    GroupCreationResponseDTO createGroup(GroupCreationDTO groupData);

    ExpenseResponseDTO addExpense(ExpenseReceivingDTO expenseData) throws UserNotFoundException,
    GroupNotFoundException, UserNotMemberOfGroupException;

    void groupSettled(SettledDTO settledDTO) throws UserNotFoundException,
            GroupNotFoundException, UserNotMemberOfGroupException;
}
