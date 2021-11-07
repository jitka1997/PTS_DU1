package sk.uniba.fmph.dcs;

import java.util.List;
import java.util.Map;

public interface InitialInterface {
    public TurnStatus getTurnStatus();

    public List<CardInterface> getDeckCards();

    public int getCopperDeck();

    public int getMarketDeck();

    public int getEstateDeck();

    public int getSmithyDeck();

    public int getVillageDeck();

    public int getFestivalDeck();

    public int getLaboratoryDeck();

    public Map<GameCardType, BuyDeck> getBuyDecks();
}
