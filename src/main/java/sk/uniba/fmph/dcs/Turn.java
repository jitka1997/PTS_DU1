package sk.uniba.fmph.dcs;

import java.util.HashMap;
import java.util.Optional;

public class Turn {
    private final HashMap<GameCardType, BuyDeck> buyDecks;
    private final TurnStatus turnStatus;
    private final Play play;
    private Hand hand;
    private final DeckInterface deck;
    private final DiscardPileInterface discardPile;

    public Turn(HashMap<GameCardType, BuyDeck> buyDecks, TurnStatus turnStatus,
                Play play, DeckInterface deck, DiscardPileInterface discardPile) {
        this.buyDecks = buyDecks;
        this.turnStatus = turnStatus;
        this.play = play;
        this.deck = deck;
        this.discardPile = discardPile;

        hand = new Hand(deck.draw(5), deck);
        playAllCopperCards();
    }

    private void playAllCopperCards() {
        for (int i = 0; i < hand.getSize(); i++) {
            try {
                CardInterface card = hand.getCard(i);
                if (card.getGameCardType() == GameCardType.GAME_CARD_TYPE_COPPER) {
                    hand.getCard(i).evaluate(turnStatus);
                    play.putTo(card);
                }
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // checks if player has a Buy and enough coins and puts bought card into the discard pile
    public boolean buyCard(GameCardType gameCardType) {
        if (gameCardType.getCost() > turnStatus.getCoins() || turnStatus.getBuys() <= 0) return false;
        Optional<CardInterface> boughtCard = buyDecks.get(gameCardType).buy();
        if (boughtCard.isEmpty()) return false;
        turnStatus.setBuys(turnStatus.getBuys() - 1);
        turnStatus.setCoins(turnStatus.getCoins() - gameCardType.getCost());
        discardPile.putInto(boughtCard.get());
        return true;
    }

    // checks if player has an Action and puts card in play
    public boolean playCard(int idOnHand) {
        if (turnStatus.getActions() <= 0) return false;
        Optional<CardInterface> playedCard = hand.play(idOnHand, turnStatus);
        if (playedCard.isEmpty()) return false;
        turnStatus.setActions(turnStatus.getActions() - 1);
        play.putTo(playedCard.get());
        return true;
    }

    // throw cards from play and hand into the discard pile
    public void endTurn() {
        for (CardInterface card : hand.throwAll()) {
            discardPile.putInto(card);
        }
        for (CardInterface card : play.throwAll()) {
            discardPile.putInto(card);
        }
    }

    // starts new turn, player draws 5 cards
    public void newTurn() {
        hand = new Hand(deck.draw(5), deck);
        playAllCopperCards();
    }
}
