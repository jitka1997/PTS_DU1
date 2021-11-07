package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class GameTest {
    Game game1;

    @Before
    public void setUp() {
        Map<GameCardType, BuyDeck> buyDecks = new HashMap<>();
        InitialInterface initial = new InitialForTest();
        DeckInterface deck = new FakeDeck(initial.getDeckCards());
        game1 = new Game(new Turn(buyDecks, initial.getTurnStatus(), new Play(), deck, new DiscardPile(new ArrayList<>())), new AtLeastNEmptyDecks(buyDecks, 3));
    }

    @Test
    public void testBuyCardOnlyInBuyPhase() {
        assertFalse(game1.buyCard(GameCardType.GAME_CARD_TYPE_COPPER));
    }

}