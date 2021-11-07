package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// initial constants of the game
public class Initial implements InitialInterface {
    private final int copperDeck;
    private final int marketDeck;
    private final int estateDeck;
    private final int smithyDeck;
    private final int villageDeck;
    private final int festivalDeck;
    private final int laboratoryDeck;
    private final List<CardInterface> deckCards;
    private final Map<GameCardType, BuyDeck> buyDecks;

    private final TurnStatus turnStatus;

    public Initial() {
        turnStatus = new TurnStatus(1, 1, 0);
        copperDeck = 10;
        marketDeck = 5;
        estateDeck = 7;
        smithyDeck = 0;
        villageDeck = 0;
        festivalDeck = 0;
        laboratoryDeck = 0;
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
        buyDecks = new HashMap<>() {{
            put(GameCardType.GAME_CARD_TYPE_COPPER, new BuyDeckCopper(copperDeck));
            put(GameCardType.GAME_CARD_TYPE_MARKET, new BuyDeckMarket(marketDeck));
            put(GameCardType.GAME_CARD_TYPE_ESTATE, new BuyDeckEstate(estateDeck));
            put(GameCardType.GAME_CARD_TYPE_SMITHY, new BuyDeckSmithy(smithyDeck));
            put(GameCardType.GAME_CARD_TYPE_VILLAGE, new BuyDeckVillage(villageDeck));
            put(GameCardType.GAME_CARD_TYPE_FESTIVAL, new BuyDeckFestival(festivalDeck));
            put(GameCardType.GAME_CARD_TYPE_LABORATORY, new BuyDeckLaboratory(laboratoryDeck));
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

    public int getSmithyDeck() {
        return smithyDeck;
    }

    public int getVillageDeck() {
        return villageDeck;
    }

    public int getFestivalDeck() {
        return festivalDeck;
    }

    public int getLaboratoryDeck() {
        return laboratoryDeck;
    }

    public Map<GameCardType, BuyDeck> getBuyDecks() {
        return buyDecks;
    }
}
