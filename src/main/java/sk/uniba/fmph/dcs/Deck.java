package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private LinkedList<CardInterface> cards;
    private final DiscardPile discardPile;

    public Deck(List<CardInterface> cards, DiscardPile discardPile){
        this.cards = new LinkedList<>(cards);
        this.discardPile = discardPile;
    }

    public List<CardInterface> draw(int count){
        List<CardInterface> cardsToDraw = new ArrayList<>();
        if(count > cards.size()) {
            for (CardInterface card : discardPile.shuffleAndGetAll()) {
                cards.addLast(card);
            }
        }
        if(count > cards.size()){
            throw new IllegalArgumentException("Deck doesn't contain enough cards");
        }
        return cardsToDraw;
    }
}
