package sk.uniba.fmph.dcs;

import java.util.List;

class FakeDeck implements DeckInterface {
    List<CardInterface> cards;

    FakeDeck(List<CardInterface> cards) {
        this.cards = cards;
    }

    @Override
    public List<CardInterface> draw(int amount) {
        return cards;
    }
}