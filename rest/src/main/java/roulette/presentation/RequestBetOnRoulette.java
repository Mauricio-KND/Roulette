package roulette.presentation;

public class RequestBetOnRoulette {
    private long rouletteId;
    private long userId;
    private int betType;
    private boolean color;
    private int rouletteBetNumber;
    private int rouletteBetAmount;

    public long getRouletteId () {
        return rouletteId;
    }

    public long getUserId () {
        return userId;
    }

    public int getBetType () {
        return betType;
    }

    public boolean getColor () {
        return color;
    }

    public int getRouletteBetNumber () {
        return rouletteBetNumber;
    }

    public int getRouletteBetAmount () {
        return rouletteBetAmount;
    }
}
