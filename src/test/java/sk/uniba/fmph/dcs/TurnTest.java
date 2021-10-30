package sk.uniba.fmph.dcs;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

// integration test on classed that are used by Turn
public class TurnTest {
    Turn turn1;
    Turn turn2;
    Turn turn3;
    HashMap<GameCardType, BuyDeck> emptyBuyDecks;
    HashMap<GameCardType, BuyDeck> buyDecks;
    TurnStatus oneBuyTurnStatus;
    TurnStatus buyOneOfEachTurnStatus;
    TurnStatus onePlayTurnStatus;
    Play play3;

    @Before
    public void setUp() {
        emptyBuyDecks = new HashMap<>() {{
            put(GameCardType.GAME_CARD_TYPE_COPPER, new BuyDeckCopper(0));
        }};
        buyDecks = new HashMap<>() {{
            put(GameCardType.GAME_CARD_TYPE_COPPER, new BuyDeckCopper(1));
            put(GameCardType.GAME_CARD_TYPE_ESTATE, new BuyDeckEstate(1));
            put(GameCardType.GAME_CARD_TYPE_MARKET, new BuyDeckMarket(1));
        }};
        oneBuyTurnStatus = new TurnStatus();
        buyOneOfEachTurnStatus = new TurnStatus();
        onePlayTurnStatus = new TurnStatus();

        turn1 = new Turn(
                emptyBuyDecks,
                oneBuyTurnStatus,
                new Play(),
                new FakeDeck(new ArrayList<>()),
                new DiscardPile(new ArrayList<>())
        );
        oneBuyTurnStatus.setBuys(1);
        oneBuyTurnStatus.setActions(0);

        turn2 = new Turn(
                buyDecks,
                buyOneOfEachTurnStatus,
                new Play(),
                new FakeDeck(new ArrayList<>()),
                new DiscardPile(new ArrayList<>())
        );
        buyOneOfEachTurnStatus.setBuys(3);
        buyOneOfEachTurnStatus.setActions(0);
        buyOneOfEachTurnStatus.setCoins(7);

        play3 = new Play();

        turn3 = new Turn(
                buyDecks,
                onePlayTurnStatus,
                play3,
                new FakeDeck(new ArrayList<>() {{
                    add(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET));
                    add(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET));
                    add(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET));
                    add(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET));
                    add(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET));
                }}),
                new DiscardPile(new ArrayList<>())
        );
        onePlayTurnStatus.setBuys(0);
        onePlayTurnStatus.setActions(1);
    }

    @Test
    public void testBuyFromEmptyBuyDeck() {
        assertFalse(turn1.buyCard(GameCardType.GAME_CARD_TYPE_COPPER));
    }

    @Test
    public void testUpdateTurnStatusAfterBuyCard() {
        turn2.buyCard(GameCardType.GAME_CARD_TYPE_COPPER);
        assertEquals(new TurnStatus(0, 2, 7).getBuys(), buyOneOfEachTurnStatus.getBuys());
        turn2.buyCard(GameCardType.GAME_CARD_TYPE_ESTATE);
        assertEquals(new TurnStatus(0, 1, 5), buyOneOfEachTurnStatus);
        turn2.buyCard(GameCardType.GAME_CARD_TYPE_MARKET);
        assertEquals(new TurnStatus(0, 0, 0), buyOneOfEachTurnStatus);
    }

    @Test
    public void testPutCardIntoPlayAfterPlayCard() {
        turn3.playCard(0);
        List<CardInterface> playedCards = play3.throwAll();
        assertEquals(1, playedCards.size());
    }
}