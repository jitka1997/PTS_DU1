package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeckTest {
    DiscardPile discardPile1;
    Deck deck1;

    @Before
    public void setUp() {
        discardPile1 = new DiscardPile(new ArrayList<>());
        deck1 = new Deck(new ArrayList<>(), discardPile1);
    }

    @Test
    public void drawFromEmptyDeckAndDiscardPile() {
        try {
            deck1.draw(5);
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Deck doesn't contain enough cards", e.getMessage());
        }
    }
}