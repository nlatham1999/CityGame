import java.util.*;

public class LumberCamp extends Business{
    LumberCamp(int owner){
        this.owner = owner;
        workers = 10;
        type = "LUMBER CAMP";
        moneyNeeded = workers*3;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "WOOD";
        amountProduced = 100;  

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("IRON TOOLS", 2);
        itemsNeeded.put("ROPE", 1);
    }
}

