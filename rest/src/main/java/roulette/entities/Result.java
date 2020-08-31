package roulette.entities;

public enum Result {
    Succeed("Succeed Open Roulette number "),
    Failed("Failed or not Found Roulette number ");

    public String result;

    Result ( String result ) {
        this.result = result;
    }
}
