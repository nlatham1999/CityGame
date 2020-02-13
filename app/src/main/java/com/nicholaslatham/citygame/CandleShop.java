package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/26/2019.
 */

import java.util.*;

public class CandleShop extends Business{
    CandleShop(int owner){
        this.owner = owner;
        workers = 5;
        type = "CANDLE SHOP";
        moneyNeeded = workers*5;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "CANDLES";
        amountProduced = 10;

        price = 1000;
        initialItems = new Tuple(2,"WOOD");

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("WAX", 2);
        itemsNeeded.put("ROPE", 2);
    }
}

