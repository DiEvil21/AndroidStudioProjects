package ru.bets.sport;

public class StatsState {
    private String command;
    private String tour;
    private String hit;
    private String pick;

    public StatsState(String command, String tour, String hit, String pick){

        this.command = command;
        this.tour = tour;
        this.hit = hit;
        this.pick = pick;

    }
    public String getCommand() {
        return this.command;
    }
    public String getTour() {
        return this.tour;
    }
    public String getHit() {
        return this.hit;
    }
    public String getPick() {
        return this.pick;
    }
}