import java.util.*;

public class IronMine extends Business{
    IronMine(int owner){
        this.owner = owner;
        workers = 10;
        type = "IRON MINE";
        moneyNeeded = workers*3;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "IRON";
        amountProduced = 100;  

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("CANDLES", 1);
        itemsNeeded.put("IRON TOOLS", 1);
        itemsNeeded.put("ROPE", 1);
    }
}

