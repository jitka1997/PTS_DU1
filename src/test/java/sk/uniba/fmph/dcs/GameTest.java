package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;


public class GameTest {
    Game game1;

    @Before
    public void setUp() {
        HashMap<GameCardType, BuyDeck> buyDecks = new HashMap<>();
        game1 = new Game(buyDecks, new Play(), new DiscardPile(new ArrayList<>()), new Initial(), new AtLeast3EmptyDecks(buyDecks));
    }

    @Test
    public void testBuyCardOnlyInBuyPhase() {
        assertFalse(game1.buyCard(GameCardType.GAME_CARD_TYPE_COPPER));
    }

}