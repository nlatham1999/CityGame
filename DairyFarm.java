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

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("WHEAT", 3);
    }
}

