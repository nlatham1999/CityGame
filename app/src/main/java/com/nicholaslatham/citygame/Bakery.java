package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/26/2019.
 */

import java.util.*;

public class Bakery extends Business{
    Bakery(int owner){
        this.owner = owner;
        workers = 5;
        type = "BAKERY";
        moneyNeeded = workers*5;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "BREAD";
        amountProduced = 10;

        price = 1000;
        initialItems = new Tuple(2,"WOOD");

        itemsNeeded.put("WHEAT", 3);
        itemsNeeded.put("MILK", 1);
        itemsNeeded.put("EGGS", 1);
    }
}

