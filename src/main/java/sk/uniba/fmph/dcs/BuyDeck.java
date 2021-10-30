package sk.uniba.fmph.dcs;

import java.util.Optional;

public abstract class BuyDeck {
    private final int cardCount;
    private final GameCardType gameCardType;

    public BuyDeck(int cardCount, GameCardType gameCardType) {
        this.cardCount = cardCount;
        this.gameCardType = gameCardType;
    }

    public int cardCount() {
        return cardCount;
    }

    public Optional<CardInterface> buy() {
        if (cardCount <= 0) {
            return Optional.empty();
        }
        return Optional.of(new GameCard(gameCardType));
    }
}
