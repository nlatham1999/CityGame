package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/26/2019.
 */

import java.util.*;

public class LumberCamp extends Business{
    LumberCamp(int owner){
        this.owner = owner;
        workers = 10;
        type = "LUMBER CAMP";
        moneyNeeded = workers*3;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "WOOD";
        amountProduced = 100;

        price = 1000;
        initialItems = new Tuple(2,"WOOD");

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("IRON TOOLS", 2);
        itemsNeeded.put("ROPE", 1);
    }
}

