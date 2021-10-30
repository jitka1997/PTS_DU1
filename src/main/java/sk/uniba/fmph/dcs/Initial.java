package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

// initial constants of the game
public class Initial {
    private final int copperDeck;
    private final int marketDeck;
    private final int estateDeck;
    private final List<CardInterface> deckCards;

    private final TurnStatus turnStatus;

    public Initial() {
        turnStatus = new TurnStatus(1, 1, 0);
        copperDeck = 10;
        marketDeck = 5;
        estateDeck = 7;
        deckCards = new ArrayList<CardInterface>() {{
            add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        }};
    }

    public TurnStatus getTurnStatus() {
        return turnStatus;
    }

    public List<CardInterface> getDeckCards() {
        return deckCards;
    }

    public int getCopperDeck() {
        return copperDeck;
    }

    public int getMarketDeck() {
        return marketDeck;
    }

    public int getEstateDeck() {
        return estateDeck;
    }
}
