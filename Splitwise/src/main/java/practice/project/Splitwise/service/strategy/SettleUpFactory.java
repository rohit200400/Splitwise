package practice.project.splitwise.service.strategy;

import practice.project.splitwise.model.SettleUpStrategyType;

public class SettleUpFactory {
    public static SettleUpStrategy getSettleUpStrategy(SettleUpStrategyType type) {
        type.toString();
        return new HeapBasedStrategy();

    }
}
