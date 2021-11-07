package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Deck implements DeckInterface {
    private final LinkedList<CardInterface> cards;
    private final DiscardPileInterface discardPile;
    private boolean isGameOver;

    public Deck(List<CardInterface> cards, DiscardPileInterface discardPile) {
        this.cards = new LinkedList<>(cards);
        this.discardPile = discardPile;
        isGameOver = false;
    }

    @Override
    public List<CardInterface> draw(int count) {
        List<CardInterface> cardsToDraw = new ArrayList<>();
        if (count > cards.size()) {
            for (CardInterface card : discardPile.shuffleAndGetAll()) {
                cards.addLast(card);
            }
        }
        if (count > cards.size()) {
            isGameOver = true;
            throw new IllegalArgumentException("Deck doesn't contain enough cards");
        }
        for(int i = 0; i < count; i++){
            cardsToDraw.add(cards.getFirst());
            cards.removeFirst();
        }
        return cardsToDraw;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
