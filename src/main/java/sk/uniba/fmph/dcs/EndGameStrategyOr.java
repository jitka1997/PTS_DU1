package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class EndGameStrategyOr implements EndGameStrategy {

    private final List<EndGameStrategy> endGameStrategies;

    public EndGameStrategyOr(List<EndGameStrategy> endGameStrategies) {
        this.endGameStrategies = new ArrayList<>();
        this.endGameStrategies.addAll(endGameStrategies);
    }

    @Override
    public boolean isGameOver() {
        for (EndGameStrategy endGameStrategy : endGameStrategies) {
            if (endGameStrategy.isGameOver()) return true;
        }
        return false;
    }
}