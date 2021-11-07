package sk.uniba.fmph.dcs;

import java.util.*;

public class AtLeastNEmptyDecks implements EndGameStrategy {
    private final HashMap<GameCardType, BuyDeck> buyDecks;
    private final int n;
    private final Set<GameCardType> typesOfBuyDecksToCheck;

    public AtLeastNEmptyDecks(HashMap<GameCardType, BuyDeck> allBuyDecks, int n) {
        this.buyDecks = allBuyDecks;
        this.n = n;
        typesOfBuyDecksToCheck = new HashSet<>();
        typesOfBuyDecksToCheck.addAll(allBuyDecks.keySet());
    }

    public AtLeastNEmptyDecks(HashMap<GameCardType, BuyDeck> buyDecks, int n, GameCardType... typesOfBuyDecksToCheck) {
        this.buyDecks = buyDecks;
        this.n = n;
        this.typesOfBuyDecksToCheck = Set.of(typesOfBuyDecksToCheck);
    }

    @Override
    public boolean isGameOver() {
        int emptyDecks = 0;
        for (BuyDeck buyDeck : buyDecks.values()) {
            if (typesOfBuyDecksToCheck.contains(buyDeck.getGameCardType()) &&
                    buyDeck.cardCount() == 0) emptyDecks++;
        }
        return (emptyDecks >= n);
    }
}
