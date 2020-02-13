package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/26/2019.
 */

import java.util.*;

public class IronSmithery extends Business{
    IronSmithery(int owner){
        this.owner = owner;
        workers = 5;
        type = "IRON SMITHERY";
        moneyNeeded = workers*5;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "IRON TOOLS";
        amountProduced = 10;

        price = 1000;
        initialItems = new Tuple(2,"WOOD");

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("IRON", 3);
    }
}
