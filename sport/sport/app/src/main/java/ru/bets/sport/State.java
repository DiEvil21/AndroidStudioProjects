package ru.bets.sport;

public class State {
    private String namber;
    private String name; // Команда
    private String i;  // игры
    private String v;  // В
    private String n;  // Н
    private String p;  // П
    private String balls;  // Мячи
    private String score;  // Очки
    private String scorePr;  // % очков
    private int flagResource; // ресурс флага

    public State(String namber, String name, String i, String v, String n, String p, String balls, String score){

        this.name=name;
        this.v = v;
        this.n = n;
        this.p = p;
        this.balls = balls;
        this.score = score;
        this.i=i;
        this.namber = namber;

    }
    public String getNamber() {return this.namber;}
    public String getName() {
        return this.name;
    }
    public String getI() {
        return this.i;
    }
    public String getVv() {
        return this.v;
    }
    public String getN() {
        return this.n;
    }
    public String getP() {
        return this.p;
    }
    public String getBalls() {
        return this.balls;
    }
    public String getScore() {
        return this.score;
    }
    public String getScorePr() {
        return this.scorePr;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getFlagResource() {
        return this.flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }
}