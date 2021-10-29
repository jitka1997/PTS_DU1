package sk.uniba.fmph.dcs;

import java.util.Map;

public class GameCard implements CardInterface{
    private final GameCardType gameCardType;

    public GameCard(GameCardType gameCardType){
        this.gameCardType = gameCardType;
    }

    @Override
    public Integer evaluate(TurnStatus ts) {
        ts.setActions(ts.getActions() + gameCardType.getPlusActions());
        ts.setBuys(ts.getBuys() + gameCardType.getPlusBuys());
        ts.setCoins(ts.getCoins() + gameCardType.getPlusCoins());
        return gameCardType.getPlusCards();
    }

    @Override
    public GameCardType getGameCardType() {
        return gameCardType;
    }
}

