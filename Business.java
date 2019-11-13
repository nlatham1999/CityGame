import java.util.*;

public class Business{
    protected int workers;
    protected String owner;
    protected String type;
    private boolean inBusiness;
    private int price;
    protected String itemsProduced;
    protected int amountProduced;
    protected Hashtable<String, Integer> itemsNeeded;
    protected int moneyNeeded;
    
    public void setOwner(String owner){
        this.owner = owner;
    }

    public String getOwner(){
        return owner;
    }

    public String getType(){
        return type;
    }

    public void setBusinessStatus(boolean yn){
        inBusiness = yn;
    }

    public boolean isInBusiness(){
        return inBusiness;
    }

    public void setPrice(int p){
        price = p;
    }

    public int getPrice(){
        return price;
    }

    public String getItemsProduced(){
        return itemsProduced;
    }

    public Hashtable getItemsNeeded(){
        return itemsNeeded;
    }

    public int getMoneyNeeded(){
        return moneyNeeded;
    }

    public int getNumberOfWorkers(){
        return workers;
    }
}