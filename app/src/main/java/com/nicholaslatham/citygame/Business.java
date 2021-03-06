package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/26/2019.
 */

import java.util.*;

public class Business{
    protected int workers;
    protected int owner;
    protected String type;
    private boolean inBusiness = true;
    private boolean forSale = false;
    protected int price;
    protected Tuple<Integer, String> initialItems;
    protected String itemsProduced;
    protected int amountProduced;
    protected Hashtable<String, Integer> itemsNeeded;
    protected int moneyNeeded;

    public Tuple<Integer, String> getInitialItems(){return initialItems;}

    public void setOwner(int owner){
        this.owner = owner;
    }

    public int getOwner(){
        return owner;
    }

    public String getType(){
        return type;
    }

    public void setBusinessStatus(boolean yn){
        inBusiness = yn;
    }

    public boolean isInBusiness(){
        return inBusiness;
    }

    public void setPrice(int p){
        price = p;
    }

    public int getPrice(){
        return price;
    }

    public String getItemsProduced(){
        return itemsProduced;
    }

    public Hashtable getItemsNeeded(){
        return itemsNeeded;
    }

    public int getMoneyNeeded(){
        return moneyNeeded;
    }

    public int getAmountProduced(){
        return amountProduced;
    }

    public int getNumberOfWorkers(){
        return workers;
    }

    public void setSaleStatus(boolean s){
        forSale = s;
    }

    public boolean isForSale(){
        return forSale;
    }
}