package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InitialForTest implements InitialInterface {
    TurnStatus turnStatus;
    private final int copperDeck;
    private final int marketDeck;
    private final int estateDeck;
    private final List<CardInterface> deckCards;
    private final HashMap<GameCardType, BuyDeck> buyDecks;

    public InitialForTest() {
        copperDeck = 2;
        marketDeck = 0;
        estateDeck = 0;
        turnStatus = new TurnStatus(1, 1, 0);
        deckCards = new ArrayList<CardInterface>() {{
            add(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        }};
        buyDecks = new HashMap<>() {{
            put(GameCardType.GAME_CARD_TYPE_COPPER, new BuyDeckCopper(copperDeck));
            put(GameCardType.GAME_CARD_TYPE_MARKET, new BuyDeckMarket(marketDeck));
            put(GameCardType.GAME_CARD_TYPE_ESTATE, new BuyDeckEstate(estateDeck));
        }};

    }

    @Override
    public TurnStatus getTurnStatus() {
        return turnStatus;
    }

    @Override
    public List<CardInterface> getDeckCards() {
        return deckCards;
    }

    @Override
    public int getCopperDeck() {
        return copperDeck;
    }

    @Override
    public int getMarketDeck() {
        return marketDeck;
    }

    @Override
    public int getEstateDeck() {
        return estateDeck;
    }

    @Override
    public int getSmithyDeck() {
        return 0;
    }

    @Override
    public int getVillageDeck() {
        return 0;
    }

    @Override
    public int getFestivalDeck() {
        return 0;
    }

    @Override
    public int getLaboratoryDeck() {
        return 0;
    }

    @Override
    public HashMap<GameCardType, BuyDeck> getBuyDecks() {
        return buyDecks;
    }
}
