import java.util.*;



private static class Building{
  private int money;
  private String owner;
  private Hashtable<String, Integer> items;
  
  Players(int startAmount){
    items = new Hashtable<String, Integer>();
    money = startAmount;
    owner = "NONE";
  }

  public void addItem(String item, int amount){
    if(items.contains(item)){
      int temp = items.get(item);
      items.put(item, temp+amount);
    }else{
      items.put(item, amount);
    }
  }

  public int getAmountOfItem(String item){
    if(items.contains(item)){
      return items.get(item);
    }else{
      return -1;
    }
  }

  public void removeItems(String item, int amount){
    if(items.contains(item)){
      int temp = items.get(item);
      if(temp > amount){  
        items.put(item, temp-amount);
      }else{
        items.put(item, 0);
      } 
    }
  }

  public void addMoney(int amount){
    money += amount;
  }

  public void removeMoney(int amount){
    money -= amount;
    if(money < 0){
      money = 0;
    }
  }

  public int getAmountOfMoney(){
    return money;
  }

  public String displayOwner(){
    return owner;
  }

  public void updateOwner(String newOwner){
    owner = newOwner;
  }
}

