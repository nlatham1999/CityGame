package com.nicholaslatham.citygame;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class GameViewModel extends ViewModel {
    private City city;
    private ArrayList<Players> players;
    private int playerNum;

    public ArrayList<Players> getPlayers(){
        if(players == null)
            return new ArrayList<Players>();
        else
            return players;
    }

    public int getNum(){
        return playerNum;
    }

    public City getCity(){
        return city;
    }

    public void setPlayers(ArrayList<Players> players){
        this.players = players;
    }

    public void setCity(City city){
        this.city = city;
    }

    public void setPlayerNum(int playerNum){
        this.playerNum = playerNum;
    }
}
