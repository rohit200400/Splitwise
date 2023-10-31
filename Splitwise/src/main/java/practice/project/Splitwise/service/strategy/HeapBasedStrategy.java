package practice.project.splitwise.service.strategy;

import practice.project.splitwise.dto.TransactionDTO;
import practice.project.splitwise.model.Expense;
import practice.project.splitwise.model.Users;
import practice.project.splitwise.model.UsersSplit;

import java.util.*;

public class HeapBasedStrategy implements SettleUpStrategy {
    @Override
    public List<TransactionDTO> settleUp(List<Expense> expenses) {
        List<TransactionDTO> transactions = new ArrayList<>();
        HashMap<Users, Double> individualAmounts = new HashMap<>();
        for (Expense expense : expenses) {
            for (UsersSplit userSplit : expense.getAmountSplit()) {
                if (individualAmounts.containsKey(userSplit.getUser())) {
                    individualAmounts.put(userSplit.getUser(),
                            individualAmounts.get(userSplit.getUser()) +
                                    userSplit.getAmount());
                } else {
                    individualAmounts.put(userSplit.getUser(),
                            userSplit.getAmount());
                }
            }
        }

        //MaxHeap -> contains all the users with positive balance
        PriorityQueue<Map.Entry<Users, Double>> maxHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(b.getValue(), a.getValue())
        );

        //MinHeap -> contains all the users with negative balance
        PriorityQueue<Map.Entry<Users, Double>> minHeap = new PriorityQueue<>(
                Comparator.comparingDouble(Map.Entry::getValue)
        );

        //populate the heaps using the values from the map
        for (Map.Entry<Users, Double> entry : individualAmounts.entrySet()) {
            if (entry.getValue() < 0) {
                minHeap.add(entry);
            } else if (entry.getValue() > 0) {
                maxHeap.add(entry);
            } else {
                System.out.println(entry.getKey().getName() + "'s is already settled up");
            }
        }

        while (!minHeap.isEmpty()) {
            Map.Entry<Users, Double> sender = minHeap.poll();
            Map.Entry<Users, Double> receiver = maxHeap.poll();
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setFromUserName(sender.getKey().getName());
            transactionDTO.setToUserName(receiver.getKey().getName());

            if (receiver.getValue() - sender.getValue() < 0) {
                sender.setValue(sender.getValue() + receiver.getValue());
                minHeap.add(sender);
                transactionDTO.setAmount(Math.abs(receiver.getValue()));
            } else {
                receiver.setValue(+receiver.getValue());
                maxHeap.add(receiver);
                transactionDTO.setAmount(Math.abs(sender.getValue()));
            }
            transactions.add(transactionDTO);
        }
        return transactions;
    }
}
