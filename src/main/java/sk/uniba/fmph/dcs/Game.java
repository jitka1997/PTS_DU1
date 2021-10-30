package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private String phase;
    private final Turn turn;
    private TurnStatus turnStatus;
    private HashMap<GameCardType, BuyDeck> buyDecks;
    private EndGameStrategy endGameStrategy;

    public Game(HashMap<GameCardType, BuyDeck> buyDecks, Play play,
                DiscardPileInterface discardPile, Initial initial, EndGameStrategy endGameStrategy) {
        phase = "play";
        this.buyDecks = buyDecks;
        this.endGameStrategy = endGameStrategy;

        //initialize instance of Turn
        turnStatus = initial.getTurnStatus();
        turn = new Turn(buyDecks, turnStatus, new Play(), new Deck(initial.getDeckCards(), discardPile), discardPile);

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
