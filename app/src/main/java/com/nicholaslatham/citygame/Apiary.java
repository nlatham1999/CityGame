package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/26/2019.
 */

import java.util.*;

public class Apiary extends Business{
    Apiary(int owner){
        this.owner = owner;
        workers = 5;
        type = "APIARY";
        moneyNeeded = workers*5;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "WAX";
        amountProduced = 10;

        price = 1000;
        initialItems = new Tuple(2,"WOOD");

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("WOOD", 4);
    }
}

