package sk.uniba.fmph.dcs;

class FakeCard implements CardInterface {
    private final GameCardType cardType;
    private final int cardsToDraw;

    public FakeCard(GameCardType cardType) {
        this.cardType = cardType;
        this.cardsToDraw = 0;
    }

    public FakeCard(GameCardType cardType, int cardToDraw) {
        this.cardType = cardType;
        this.cardsToDraw = cardToDraw;
    }

    public Integer evaluate(TurnStatus t) {
        return cardsToDraw;
    }

    public GameCardType getGameCardType() {
        return cardType;
    }
}