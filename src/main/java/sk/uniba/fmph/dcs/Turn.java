package sk.uniba.fmph.dcs;

import java.util.HashMap;
import java.util.Optional;

public class Turn {
    private final HashMap<GameCardType, BuyDeck> buyDecks;
    private final TurnStatus turnStatus;
    private final Play play;
    private Hand hand;
    private final DeckInterface deck;
    private final DiscardPile discardPile;

    public Turn(HashMap<GameCardType, BuyDeck> buyDecks, TurnStatus turnStatus, Play play, DeckInterface deck, DiscardPile discardPile) {
        turnStatus.setBuys(1);
        turnStatus.setActions(1);

        hand = new Hand(deck.draw(5), deck);

        this.buyDecks = buyDecks;
        this.turnStatus = turnStatus;
        this.play = play;
        this.deck = deck;
        this.discardPile = discardPile;
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
    public void newTurn() {
        for (CardInterface card : hand.throwAll()) {
            discardPile.putInto(card);
        }
        for (CardInterface card : play.throwAll()) {
            discardPile.putInto(card);
        }
        hand = new Hand(deck.draw(5), deck);
    }
}
