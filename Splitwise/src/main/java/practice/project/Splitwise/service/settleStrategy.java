package practice.project.Splitwise.service;

import practice.project.Splitwise.dto.TransactionDTO;
import practice.project.Splitwise.model.Client;
import practice.project.Splitwise.model.Expense;
import practice.project.Splitwise.model.UsersSplit;

import java.util.*;

public interface settleStrategy {
    default List<TransactionDTO> settleUP(List<Expense> expenses, List<Client> users) {
        List<TransactionDTO> transactions = new ArrayList<>();
        HashMap<Client, Double> individualAmounts = new HashMap<>();
        for (Expense expense : expenses) {
            for (UsersSplit userSplit : expense.getAmountSplit()) {
                if (individualAmounts.containsKey(userSplit.getUser())) {
                    individualAmounts.put(userSplit.getUser(),
                            individualAmounts.get(userSplit.getUser()) +
                                    userSplit.getAmount());
                }
            }
        }

        //MaxHeap -> contains all the users with positive balance
        PriorityQueue<Map.Entry<Client, Double>> maxHeap = new PriorityQueue<>(
                (a, b) -> Double.compare(b.getValue(), a.getValue())
        );

        //MinHeap -> contains all the users with negative balance
        PriorityQueue<Map.Entry<Client, Double>> minHeap = new PriorityQueue<>(
                Comparator.comparingDouble(Map.Entry::getValue)
        );

        //populate the heaps using the values from the map
        for (Map.Entry<Client, Double> entry : individualAmounts.entrySet()) {
            if (entry.getValue() < 0) {
                minHeap.add(entry);
            } else if (entry.getValue() > 0) {
                maxHeap.add(entry);
            } else {
                System.out.println(entry.getKey().getName() + "'s is already settled up");
            }
        }

        while (!minHeap.isEmpty()) {
            Map.Entry<Client, Double> sender = minHeap.poll();
            Map.Entry<Client, Double> receiver = maxHeap.poll();
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setSender(sender.getKey());
            transactionDTO.setReceiver(receiver.getKey());

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
