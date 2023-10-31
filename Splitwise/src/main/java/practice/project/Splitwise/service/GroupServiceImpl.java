package practice.project.splitwise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.project.splitwise.dto.TransactionDTO;
import practice.project.splitwise.exception.GroupNotFoundException;
import practice.project.splitwise.model.SettleUpStrategyType;
import practice.project.splitwise.model.UsersGroup;
import practice.project.splitwise.repository.GroupRepo;
import practice.project.splitwise.service.strategy.SettleUpFactory;
import practice.project.splitwise.service.strategy.SettleUpStrategy;

import java.util.List;
import java.util.Optional;
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepo groupRepository;
    @Override
    public List<TransactionDTO> settleUpByGroupId(int groupId) throws GroupNotFoundException {
        SettleUpStrategy strategy = SettleUpFactory.getSettleUpStrategy(SettleUpStrategyType.HeapBased);
        Optional<UsersGroup> savedGroup = groupRepository.findById(groupId);
        if(savedGroup.isEmpty()){
            throw new GroupNotFoundException("Group for the given id was not found. Id : " + groupId);
        }
        return strategy.settleUp(savedGroup.get().getExpenses());
    }
}
