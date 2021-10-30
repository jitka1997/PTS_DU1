package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface DiscardPileInterface {
    public Optional<CardInterface> getTopCard();

    public void addCards(List<CardInterface> cards);

    public int getSize();

    public void putInto(CardInterface card);

    public List<CardInterface> shuffleAndGetAll();
}
