package roulette.domain;

public class Bet {

    private Long id;
    private Roulette roulette;
    private User user;
    private  boolean color = true; //True=Red, False=Black
    public int betAmount = 0; //0-10000
    public int betNumber = 0; //0-36

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Roulette getRoulette () {
        return roulette;
    }

    public void setRoulette ( Roulette roulette ) {
        this.roulette = roulette;
    }

    public User getUser () {
        return user;
    }

    public void setUser ( User user ) {
        this.user = user;
    }

    public boolean isColor () {
        return color;
    }

    public void setColor ( boolean color ) {
        this.color = color;
    }

    public int getBetAmount () {
        return betAmount;
    }

    public void setBetAmount ( int betAmount ) {
        this.betAmount = betAmount;
    }

    public int getBetNumber () {
        return betNumber;
    }

    public void setBetNumber ( int betNumber ) {
        this.betNumber = betNumber;
    }
}
