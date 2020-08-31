package roulette.entities;

public enum Status {
    OPEN("Open"),
    CLOSED("Closed");

    String status;

    Status ( String status ) {
        this.status = status;
    }
}
