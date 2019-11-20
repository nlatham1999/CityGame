import java.util.*;

public class RopeProducer extends Business{
    RopeProducer(int owner){
        this.owner = owner;
        workers = 7;
        type = "ROPE PRODUCER";
        moneyNeeded = workers*4;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "ROPE";
        amountProduced = 10;  

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("HEMP", 3);
    }
}

