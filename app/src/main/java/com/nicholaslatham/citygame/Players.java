package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/26/2019.
 */

import java.io.Serializable;
import java.util.*;

public class Players implements Serializable {
    private int money;
    String name;
    private Hashtable<String, Integer> items;

    Players(int startAmount, String name){
        this.name = name;
        items = new Hashtable<String, Integer>();
        money = startAmount;
        addInitialItems();
    }

    private void addInitialItems(){
        items.put("WHEAT", 10);
        items.put("GOLD", 10);
        items.put("BREAD", 10);
        items.put("IRON TOOLS", 10);
        items.put("WOOD", 10);
        items.put("CANDLES", 10);
        items.put("ROPE", 10);
        items.put("MILK", 10);
        items.put("EGGS", 10);
        items.put("IRON", 10);
        items.put("WAX", 10);
        items.put("HEMP", 10);
    }

    public String getName(){
        return name;
    }

    public Hashtable<String, Integer> getItems(){
        return items;
    }

    //add items to players inventory
    public void addItem(String item, int amount){
        if(items.contains(item)){
            int temp = items.get(item);
            items.put(item, temp+amount);
        }else{
            items.put(item, amount);
        }
    }

    //get the amount of a certain item that a player has
    public int getAmountOfItem(String item){
        if(items.containsKey(item)){
            return items.get(item);
        }else{
            return -1;
        }
    }

    //remove an item from the inventory
    public void removeItems(String item, int amount){
        if(items.contains(item)){
            int temp = items.get(item);
            if(temp > amount){
                items.put(item, temp-amount);
            }else{
                items.put(item, 0);
            }
        }
    }

    //add money to the inventory
    public void addMoney(int amount){
        money += amount;
    }

    //remove money from the inventory
    public void removeMoney(int amount){
        money -= amount;
        if(money < 0){
            money = 0;
        }
    }

    //get the amount of money that a player has
    public int getAmountOfMoney(){
        return money;
    }
}


