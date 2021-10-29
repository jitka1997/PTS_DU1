package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class HandTest {
    private Hand hand1;
    private DeckInterface deck1;

    private void assertTopIs(DiscardPile pile, String string) {
        assertTrue(pile.getTopCard().isPresent());
        assertEquals(pile.getTopCard().get().getGameCardType().getName(), string);
    }

    private void assertTopIsNone(DiscardPile pile) {
        assertTrue(pile.getTopCard().isEmpty());
    }

    @Before
    public void setUp() {
        deck1 = new FakeDeck(new ArrayList<>());
        hand1 = new Hand(new ArrayList<>() {{
            add(new FakeCard(GameCardType.GAME_CARD_TYPE_MARKET, 1));
            add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        }}, deck1);
    }

    @Test
    public void testCardIndexOutOfBounds() {
        try {
            hand1.isActionCard(3);
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("No such card on hand", e.getMessage());
        }
    }

    @Test
    public void testPlayCardThatIsNotOnHand() {
        assertTrue(hand1.play(3, new TurnStatus()).isEmpty());
    }

    @Test
    public void returnPlayedCard() {
        Optional<CardInterface> card = hand1.play(0, new TurnStatus());
        assertTrue(card.isPresent());
        assertEquals(GameCardType.GAME_CARD_TYPE_MARKET, card.get().getGameCardType());
    }
}