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

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("IRON TOOLS", 2);
    }
}




