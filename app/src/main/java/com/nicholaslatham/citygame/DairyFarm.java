package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/26/2019.
 */

import java.util.*;

public class DairyFarm extends Business{
    DairyFarm(int owner){
        this.owner = owner;
        workers = 10;
        type = "DAIRY FARM";
        moneyNeeded = workers*3;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "MILK";
        amountProduced = 10;

        price = 1000;
        initialItems = new Tuple(2,"WOOD");

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("WHEAT", 3);
    }
}


