import java.util.*;

public class EggFarm extends Business{
    EggFarm(int owner){
        this.owner = owner;
        workers = 10;
        type = "EGG FARM";
        moneyNeeded = workers*3;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "EGGS";
        amountProduced = 10;  

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("WHEAT", 3);
    }
}

