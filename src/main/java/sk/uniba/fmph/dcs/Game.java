package sk.uniba.fmph.dcs;

import java.util.HashMap;

public class Game {
    private String phase;
    private final Turn turn;
    private final EndGameStrategy endGameStrategy;

    public Game(Turn turn, EndGameStrategy endGameStrategy) {
        phase = "play";
        this.endGameStrategy = endGameStrategy;

        //initialize instance of Turn
        this.turn = turn;

    }

    public boolean playCard(int idOnHand) {
        if (phase.equals("buy")) return false;
        return turn.playCard(idOnHand);
    }

    public boolean endPlayCardPhase() {
        phase = "buy";
        return true;
    }

    public boolean buyCard(GameCardType gameCardType) {
        if (phase.equals("play")) return false;
        return turn.buyCard(gameCardType);
    }

    // returns true if game is over
    public boolean endTurn() {
        turn.endTurn();
        if (endGameStrategy.isGameOver()) return true;
        turn.newTurn();
        phase = "play";
        return false;
    }
}
