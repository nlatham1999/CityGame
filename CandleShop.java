import java.util.*;

public class CandleShop extends Business{
    CandleShop(String owner){
        this.owner = owner;
        workers = 5;
        type = "CANDLE SHOP";
        moneyNeeded = workers*5;
        itemsNeeded = new Hashtable<String, Integer>();
        itemsProduced = "CANDLES";
        amountProduced = 10;  

        itemsNeeded.put("BREAD", 1);
        itemsNeeded.put("WAX", 2);
        itemsNeeded.put("ROPE", 2);
    }
}

