import java.util.*;

public class IronSmithery extends Business{
    IronSmithery(String owner){
        this.owner = owner;
        workers = 5;
        type = "IRON SMITHERY";
        moneyNeeded = workers*5;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "IRON TOOLS";
        amountProduced = 10;  

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("IRON", 3);
    }
}

