package sk.uniba.fmph.dcs;

public class NothingToDraw implements EndGameStrategy {
    private final DeckInterface deck;

    public NothingToDraw(DeckInterface deck) {
        this.deck = deck;
    }

    @Override
    public boolean isGameOver() {
        return ((Deck) deck).isGameOver();
    }
}
