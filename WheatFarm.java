import java.util.*;

public class WheatFarm extends Business{
    WheatFarm(String owner){
        this.owner = owner;
        workers = 10;
        type = "WHEAT FARM";
        moneyNeeded = workers*4;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "WHEAT";
        amountProduced = 10;  

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("IRON TOOLS", 1);
        itemsNeeded.put("WOOD", 1);
    }
}

