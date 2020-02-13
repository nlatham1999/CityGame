package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/26/2019.
 */

import java.util.*;

public class HempFarm extends Business{
    HempFarm(int owner){
        this.owner = owner;
        workers = 10;
        type = "HEMP FARM";
        moneyNeeded = workers*4;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "HEMP";
        amountProduced = 10;

        price = 1000;
        initialItems = new Tuple(2,"WOOD");

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("IRON TOOLS", 2);
    }
}

