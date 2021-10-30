package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NonShufflingDiscardPileTest {
    DiscardPileInterface pile1;
    List<CardInterface> cards1;

    @Before
    public void setUp(){
        cards1 = new ArrayList<>(){{
            add(new FakeCard(GameCardType.GAME_CARD_TYPE_MARKET));
            add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        }};
        pile1 = new NonShufflingDiscardPile(cards1);
    }

    @Test
    public void testPileDoestShuffle(){
        int i = 0;
        for(CardInterface card : pile1.shuffleAndGetAll()){
            assertEquals(cards1.get(i), card);
            i++;
        }
    }
}