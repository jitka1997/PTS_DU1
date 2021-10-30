package sk.uniba.fmph.dcs;

import java.util.HashMap;

public class AtLeast3EmptyDecks implements EndGameStrategy {
    private final HashMap<GameCardType, BuyDeck> buyDecks;

    public AtLeast3EmptyDecks(HashMap<GameCardType, BuyDeck> buyDecks) {
        this.buyDecks = buyDecks;
    }

    @Override
    public boolean isGameOver() {
        int emptyDecks = 0;
        for (BuyDeck buyDeck : buyDecks.values()) {
            if (buyDeck.cardCount() == 0) emptyDecks++;
        }
        return (emptyDecks >= 3);
    }
}
