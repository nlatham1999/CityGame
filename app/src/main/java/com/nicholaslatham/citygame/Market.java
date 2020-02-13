package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/26/2019.
 */
import java.util.*;

public class Market {
    private static ArrayList<Integer> price;
    private static ArrayList<Integer> quantity;
    private static ArrayList<String> item;
    private static ArrayList<Integer> seller;

    Market(){
        price = new ArrayList<Integer>();
        quantity = new ArrayList<Integer>();
        item = new ArrayList<String>();
        seller = new ArrayList<Integer>();
    }

    public static void addTrade(int itemPrice, String itemType, int itemQuantity, int player){
        price.add(itemPrice);
        quantity.add(itemQuantity);
        item.add(itemType);
        seller.add(player);
    }

    public static void removeTrade(int tradeNum){
        price.remove(tradeNum);
        quantity.remove(tradeNum);
        item.remove(tradeNum);
        seller.remove(tradeNum);
    }

    public int numberOfTrades(){
        return price.size();
    }

    public int getPrice(int tradeNum){
        return price.get(tradeNum);
    }

    public int getQuantity(int tradeNum){
        return quantity.get(tradeNum);
    }

    public String getItem(int tradeNum){
        return item.get(tradeNum);
    }

    public int getSeller(int tradeNum){
        return seller.get(tradeNum);
    }
}
