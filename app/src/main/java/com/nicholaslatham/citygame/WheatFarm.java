package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/26/2019.
 */

import java.util.*;

public class WheatFarm extends Business{
    WheatFarm(int owner){
        this.owner = owner;
        workers = 10;
        type = "WHEAT FARM";
        moneyNeeded = workers*4;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "WHEAT";
        amountProduced = 10;

        price = 1000;
        initialItems = new Tuple(2,"WOOD");

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("IRON TOOLS", 1);
        itemsNeeded.put("WOOD", 1);
    }
}
