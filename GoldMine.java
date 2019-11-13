import java.util.*;

public class GoldMine extends Business{
    GoldMine(String owner){
        this.owner = owner;
        workers = 10;
        type = "GOLD MINE";
        moneyNeeded = workers*4;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "GOLD";
        amountProduced = 100;  

        itemsNeeded.put("BREAD", workers);
        itemsNeeded.put("CANDLES", workers);
        itemsNeeded.put("IRON", workers);
        itemsNeeded.put("ROPE", workers/2);
    }
}

