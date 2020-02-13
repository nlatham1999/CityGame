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

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("WOOD", 4);
    }
}

