package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private String phase;
    private final Turn turn;
    private TurnStatus turnStatus;
    private HashMap<GameCardType, BuyDeck> buyDecks;
    private EndGameStrategy endGameStrategy;
    private boolean hasWon;

    public Game() {
        phase = "play";
        hasWon = false;

        //initialize instance of Turn
        Initial initial = new Initial();
        buyDecks = new HashMap<>() {{
            put(GameCardType.GAME_CARD_TYPE_COPPER, new BuyDeckCopper(initial.getCopperDeck()));
            put(GameCardType.GAME_CARD_TYPE_ESTATE, new BuyDeckEstate(initial.getEstateDeck()));
            put(GameCardType.GAME_CARD_TYPE_MARKET, new BuyDeckMarket(initial.getMarketDeck()));
        }};
        turnStatus = new TurnStatus();
        DiscardPileInterface discardPile = new DiscardPile(initial.getDeckCards());
        DeckInterface deck = new Deck(new ArrayList<>(), discardPile);
        turn = new Turn(buyDecks, turnStatus, new Play(), deck, discardPile);

        endGameStrategy = new AtLeast3EmptyDecks(buyDecks);
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
        if(endGameStrategy.isGameOver()){
            hasWon = hasWon();
            return true;
        }
        turn.newTurn();
        phase = "play";
        return false;
    }

    private boolean hasWon(){
        return true;
    }
}
