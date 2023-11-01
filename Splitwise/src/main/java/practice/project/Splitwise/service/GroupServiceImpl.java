package practice.project.splitwise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.project.splitwise.dto.*;
import practice.project.splitwise.exception.GroupNotFoundException;
import practice.project.splitwise.exception.UserNotFoundException;
import practice.project.splitwise.exception.UserNotMemberOfGroupException;
import practice.project.splitwise.model.*;
import practice.project.splitwise.repository.ExpenseRepo;
import practice.project.splitwise.repository.GroupRepo;
import practice.project.splitwise.repository.UserRepo;
import practice.project.splitwise.repository.UsersSplitRepo;
import practice.project.splitwise.service.strategy.SettleUpFactory;
import practice.project.splitwise.service.strategy.SettleUpStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepo groupRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private UsersSplitRepo usersSplitRepo;

    @Override
    public List<TransactionDTO> settleUpByGroupId(int groupId) throws GroupNotFoundException {
        SettleUpStrategy strategy = SettleUpFactory.getSettleUpStrategy(SettleUpStrategyType.HeapBased);
        Optional<UsersGroup> savedGroup = groupRepository.findById(groupId);
        if (savedGroup.isEmpty()) {
            throw new GroupNotFoundException("Group for the given id was not found. Id : " + groupId);
        }
        return strategy.settleUp(savedGroup.get().getExpenses());
    }

    @Override
    public GroupCreationResponseDTO createGroup(GroupCreationDTO groupData) {
        // Create a group
        UsersGroup group = new UsersGroup();
        group.setName(groupData.getName());
        group.setDescription(groupData.getDescription());
        group.setDefaultCurrency(groupData.getCurrency());
        UsersGroup savedGroup = groupRepository.save(group);

        // add the users
        List<Users> allUsers = new ArrayList<>();
        for (Users user : groupData.getUsersList()) {
            Users currUser;
            Optional<Users> existingUser = userRepo.findByMail(user.getMail());
            if (existingUser.isPresent()) {
                existingUser.get().setName(user.getName());
                currUser = userRepo.save(existingUser.get());
            } else {
                currUser = userRepo.save(user);
            }
            allUsers.add(currUser);
        }
        savedGroup.setUsers(allUsers);
        savedGroup = groupRepository.save(savedGroup);

        //creating response dto
        GroupCreationResponseDTO responseDTO = new GroupCreationResponseDTO();
        responseDTO.setName(savedGroup.getName());
        responseDTO.setDescription(savedGroup.getDescription());
        responseDTO.setCurrency(savedGroup.getDefaultCurrency());
        responseDTO.setTotalSpending(0.0);
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (Users user : savedGroup.getUsers()) {
            userResponseDTOList.add(new UserResponseDTO(user.getId(), user.getName(), user.getMail()));
        }
        responseDTO.setUsersList(userResponseDTOList);
        return responseDTO;
    }

    @Override
    public ExpenseResponseDTO addExpense(ExpenseReceivingDTO expenseData) throws UserNotFoundException,
    GroupNotFoundException, UserNotMemberOfGroupException{
        // validations of group and User
        Optional<Users> payingUser = userRepo.findById(expenseData.getPaidByUserID());
        if(payingUser.isEmpty()){
            throw new UserNotFoundException("Paid user not found in the database.");
        }

        Optional<UsersGroup> group = groupRepository.findById(expenseData.getGroupID());
        if(group.isEmpty()) {
            throw new GroupNotFoundException("Group not found in the database.");
        }
        if(!payingUser.get().getUsersGroups().contains(group.get())) {
            throw new UserNotMemberOfGroupException("User who paid is not a member of group.");
        }

        Expense expense = new Expense(expenseData.getAmount(), expenseData.getDescription(), payingUser.get());
        List<UsersSplit> usersSplits = expense.getAmountSplit();
        for (UserSplitReceivingDTO split : expenseData.getUserSplit()
        ) {
            Optional<Users> currUser = userRepo.findById(split.getUserId());
            if(currUser.isEmpty()){
                throw new UserNotFoundException("User not found in the database.");
            }
            if(!currUser.get().getUsersGroups().contains(group.get())) {
                throw new UserNotMemberOfGroupException("User who paid is not a member of group.");
            }
            usersSplits.add(new UsersSplit(currUser.get(), -1 * split.getAmount()));
        }
        for (UsersSplit split : usersSplits
        ) {
            split = usersSplitRepo.save(split);
        }
        expense.setAmountSplit(usersSplits);
        Expense savedExpense = expenseRepo.save(expense);
        List<Expense> allExpenses = group.get().getExpenses();
        allExpenses.add(savedExpense);
        group.get().setExpenses(allExpenses);
        Double amountSpent = group.get().getTotalAmountSpent() + expenseData.getAmount();
        group.get().setTotalAmountSpent(amountSpent);
        UsersGroup savedGroup = groupRepository.save(group.get());

        ExpenseResponseDTO responseDTO = new ExpenseResponseDTO();
        responseDTO.setName(savedGroup.getName());
        responseDTO.setDescription(savedGroup.getDescription());
        responseDTO.setCurrency(savedGroup.getDefaultCurrency());
        responseDTO.setTotalSpending(savedGroup.getTotalAmountSpent());

//        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
//        for (Users user : savedGroup.getUsers()) {
//            userResponseDTOList.add(new UserResponseDTO(user.getId(), user.getName(), user.getMail()));
//        }
//        responseDTO.setUsersList(userResponseDTOList);

        List<ExpenseDTO> expenseResponse = new ArrayList<>();
        for (Expense ex: savedGroup.getExpenses()
             ) {
            expenseResponse.add(new ExpenseDTO(ex.getAmount(),
                    ex.getDescription(), ex.getPaidBy().getName()));
        }
        responseDTO.setExpenseDTOList(expenseResponse);

        return responseDTO;
        // todo: create a proper response type
    }


}
