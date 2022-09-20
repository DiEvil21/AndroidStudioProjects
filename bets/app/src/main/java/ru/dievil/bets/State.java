package ru.dievil.bets;

public class State {
    private String btn, url, img;

    public State(String btn, String url, String img){

        this.btn = btn;
        this.url = url;
        this.img = img;

    }
    public String getBtn(){
        return this.btn;
    }

    public String getUrl() {
        return this.url;
    }

    public String getImg() {
        return this.img;
    }



}

