package practice.project.splitwise.service.initilazation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.project.splitwise.model.*;
import practice.project.splitwise.repository.ExpenseRepo;
import practice.project.splitwise.repository.GroupRepo;
import practice.project.splitwise.repository.UserRepo;
import practice.project.splitwise.repository.UsersSplitRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitServiceImpl implements InitService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private UsersSplitRepo usersSplitRepo;

    @Override
    public void initialise() {
        UsersGroup group = new UsersGroup();
        group.setName("First Group");
        group.setDescription(" GD ");
        group.setDefaultCurrency(Currency.INR);
        UsersGroup savedGroup = groupRepo.save(group);

        Users user1 = new Users();
        user1.setName("user1");
        user1.setMail("user1@email.com");

        Users user2 = new Users();
        user2.setName("user2");
        user2.setMail("user2@email.com");

        Users user3 = new Users();
        user3.setName("user3");
        user3.setMail("user3@email.com");

        Users user4 = new Users();
        user4.setName("user4");
        user4.setMail("user4@email.com");

        Users user5 = new Users();
        user5.setName("user5");
        user5.setMail("user5@email.com");

        Users savedUser1 = userRepo.save(user1);
        Users savedUser2 = userRepo.save(user2);
        Users savedUser3 = userRepo.save(user3);
        Users savedUser4 = userRepo.save(user4);
        Users savedUser5 = userRepo.save(user5);

        savedGroup.setUsers(List.of(savedUser1, savedUser2, savedUser3, savedUser4, savedUser5));
        savedGroup = groupRepo.save(savedGroup);

        List<Expense> expenses = new ArrayList<>();

        Expense expense1 = new Expense(100.0, "snacks", user1);
        expense1 = expense1.splitEqually(List.of(savedUser1, savedUser2, savedUser3, savedUser4, savedUser5));
        expenses.add(expense1);
        for (UsersSplit split : expense1.getAmountSplit()
        ) {
            usersSplitRepo.save(split);
        }
        expenseRepo.save(expense1);


        Expense expense2 = new Expense(1000.0, "food", user2);
        expense2.splitEqually(List.of(savedUser1, savedUser2, savedUser3, savedUser4, savedUser5));
        expenses.add(expense2);
        for (UsersSplit split : expense2.getAmountSplit()
        ) {
            usersSplitRepo.save(split);
        }
        expenseRepo.save(expense2);
        savedGroup.setTotalAmountSpent(expense1.getAmount() + expense2.getAmount());
        savedGroup.setExpenses(expenses);
        groupRepo.save(savedGroup);
    }
}
