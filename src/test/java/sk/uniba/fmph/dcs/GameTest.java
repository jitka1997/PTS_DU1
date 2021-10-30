package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class GameTest {
    Game game1;
    Game game2;

    @Before
    public void setUp() {
        game1 = new Game();
        game2 = new Game();
        game2.endPlayCardPhase();
    }

    @Test
    public void testBuyCardOnlyInBuyPhase() {
        assertFalse(game1.buyCard(GameCardType.GAME_CARD_TYPE_COPPER));
    }

}