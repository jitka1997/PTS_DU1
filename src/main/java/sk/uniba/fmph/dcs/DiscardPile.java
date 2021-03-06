package sk.uniba.fmph.dcs;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DiscardPile implements DiscardPileInterface{
    private List<CardInterface> cards;

    public DiscardPile(List<CardInterface> cards) {
        this.cards = cards;
    }

    @Override
    public Optional<CardInterface> getTopCard() {
        if (cards.isEmpty()) return Optional.empty();
        return Optional.of(cards.get(cards.size() - 1));
    }

    @Override
    public void addCards(List<CardInterface> cards) {
        this.cards.addAll(cards);
    }

    @Override
    public int getSize() {
        return cards.size();
    }

    @Override
    public void putInto(CardInterface card){
        cards.add(card);
    }

    @Override
    public List<CardInterface> shuffleAndGetAll() {
        Collections.shuffle(cards);
        List<CardInterface> cardsToSend = cards;
        cards = new ArrayList<>();
        return cardsToSend;
    }
}
        
        
