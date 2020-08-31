package roulette.entities;

public enum Result {
    Succeed("Succeed"),
    Failed("Failed");

    public String result;

    Result ( String result ) {
        this.result = result;
    }
}
