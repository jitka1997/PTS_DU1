package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;


public class IntegrationTest {
    Game game1;
    InitialInterface initial;
    HashMap<GameCardType, BuyDeck> buyDecks;
    Play play;
    DiscardPileInterface pile;
    EndGameStrategy endGameStrategy;

    @Before
    public void setUp() {
        initial = new InitialForTest();
        buyDecks = initial.getBuyDecks();
        play = new Play();
        pile = new NonShufflingDiscardPile(new ArrayList<>());
        endGameStrategy = new AtLeastNEmptyDecks(buyDecks, 3);
        Turn turn = new Turn(buyDecks, initial.getTurnStatus(), play, new Deck(initial.getDeckCards(), pile), pile);
        game1 = new Game(turn, endGameStrategy);
    }

    // player has 1 market, 1 estate and 3 coppers on hand
    @Test
    public void testOneRound() {
        assertEquals(3, play.getNumberOfCardsInPlay());
        boolean cardPlayed = game1.playCard(0); // market card -> +1 estate card on hand
        assertTrue(cardPlayed);
        assertEquals(4, play.getNumberOfCardsInPlay());
        game1.endPlayCardPhase();
        game1.buyCard(GameCardType.GAME_CARD_TYPE_COPPER);
        assertTrue(pile.getTopCard().isPresent());
        assertEquals(GameCardType.GAME_CARD_TYPE_COPPER, pile.getTopCard().get().getGameCardType());
        assertFalse(game1.endTurn());
        assertEquals(0, pile.getSize());
    }

}