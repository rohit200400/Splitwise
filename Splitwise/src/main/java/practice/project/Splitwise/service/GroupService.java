package practice.project.splitwise.service;

import practice.project.splitwise.dto.TransactionDTO;
import practice.project.splitwise.exception.GroupNotFoundException;

import java.util.List;

public interface GroupService {
    List<TransactionDTO> settleUpByGroupId(int groupId) throws GroupNotFoundException;

}
