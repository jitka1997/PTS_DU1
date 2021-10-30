package sk.uniba.fmph.dcs;


import java.util.ArrayList;
import java.util.List;

public class Play {
    private List<CardInterface> cards;

    public Play() {
        cards = new ArrayList<>();
    }

    public void putTo(CardInterface card) {
        cards.add(card);
    }

    public int getNumberOfCardsInPlay() {
        return cards.size();
    }

    public List<CardInterface> throwAll() {
        List<CardInterface> result = cards;
        cards = new ArrayList<>();
        return result;
    }

}


