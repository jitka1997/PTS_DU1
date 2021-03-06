package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hand {
    private List<CardInterface> cards;
    private final DeckInterface deck;

    public Hand(List<CardInterface> cards, DeckInterface deck) {
        this.cards = cards;
        this.deck = deck;
    }

    public Optional<CardInterface> play(int id, TurnStatus ts) {
        int numberOfCardsToDraw = 0;
        CardInterface playedCard = null;
        try {
            playedCard = cards.get(id);
            numberOfCardsToDraw = playedCard.evaluate(ts);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }

        try {
            cards.addAll(deck.draw(numberOfCardsToDraw));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
        if (playedCard != null) {
            cards.remove(id);
            return Optional.of(playedCard);
        }
        return Optional.empty();
    }

    public boolean isActionCard(int id) {
        if (id < 0 || id > cards.size() - 1) {
            throw new IllegalArgumentException("No such card on hand");
        }
        return cards.get(id).getGameCardType().isAction();
    }

    public CardInterface getCard(int id) {
        if (id < 0 || id > cards.size() - 1) throw new IllegalArgumentException("No such card on Hand");
        return cards.get(id);
    }

    public int getSize() {
        return cards.size();
    }

    public List<CardInterface> throwAll() {
        List<CardInterface> cardsToThrow = cards;
        cards = new ArrayList<>();
        return cardsToThrow;
    }
}
