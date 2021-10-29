package sk.uniba.fmph.dcs;

import java.util.List;
import java.util.Optional;

public class Hand {
    private final List<CardInterface> cards;
    private final DeckInterface deck;

    public Hand(List<CardInterface> cards, DeckInterface deck) {
        this.cards = cards;
        this.deck = deck;
    }

    public Optional<CardInterface> play(int id, TurnStatus ts) {
        int numberOfCardsToDraw = 0;
        CardInterface playedCard = null;
        try {
            if (isActionCard(id)) {
                playedCard = cards.get(id);
                numberOfCardsToDraw = playedCard.evaluate(ts);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }

        cards.addAll(deck.draw(numberOfCardsToDraw));
        if (playedCard != null) {
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
}
