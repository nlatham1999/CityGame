import java.util.*;

public class Bakery extends Business{
    Bakery(String owner){
        this.owner = owner;
        workers = 5;
        type = "BAKERY";
        moneyNeeded = workers*5;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "BREAD";
        amountProduced = 10;  

        itemsNeeded.put("WHEAT", 3);
        itemsNeeded.put("MILK", 1);
        itemsNeeded.put("EGGS", 1);
    }
}

