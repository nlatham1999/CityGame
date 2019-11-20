import java.util.*;

public class City{
    private static ArrayList<Business> city;  //List of all the businesses

    City(int start){
        city = new ArrayList<Business>();
        for(int i = 0; i < start; i++){
            Apiary tempA = new Apiary(-1);
            Bakery tempB = new Bakery(-1);
            CandleShop tempC = new CandleShop(-1);
            DairyFarm tempD = new DairyFarm(-1);
            EggFarm tempE = new EggFarm(-1);
            GoldMine tempG = new GoldMine(-1);
            HempFarm tempH = new HempFarm(-1);
            IronMine tempI = new IronMine(-1);
            IronSmithery tempIS = new IronSmithery(-1);
            LumberCamp tempL = new LumberCamp(-1);
            RopeProducer tempP = new RopeProducer(-1);
            WheatFarm tempW = new WheatFarm(-1);

            city.add(tempA);
            city.add(tempB);
            city.add(tempC);
            city.add(tempD);
            city.add(tempE);
            city.add(tempG);
            city.add(tempH);
            city.add(tempI);
            city.add(tempIS);
            city.add(tempL);
            city.add(tempP);
            city.add(tempW);
        }
    }

    public void printCity(){
        for(int i = 0; i < city.size(); i++){
            System.out.println(city.get(i).getType() + " " + city.get(i).getOwner());
        }
    }

    public ArrayList<Business> getHoldings(int owner){
        ArrayList<Business> holdings = new ArrayList<Business>();
        for(int i = 0; i < city.size(); i++){
            if(city.get(i).getOwner() == owner){
                holdings.add(city.get(i));
            }
        }
        return holdings;
    }

    public static void setOwner(int owner, int place){
        Business b = city.get(place);
        b.setOwner(owner);
        city.set(place, b);
    }


    //iterates through each of the businesses
    //  gets the owner
    //  gets the keys of the items needed
    //  iterates through the keys
    //      subtracts item needed from the player
    public static ArrayList<Players> doFinances(ArrayList<Players> players){

        for(int i = 0; i < city.size(); i++){
            Business currB = city.get(i);
            int owner = currB.getOwner();
            if(owner > -1){
                Players currP = players.get(owner);
                Hashtable<String, Integer> items = currB.getItemsNeeded();
                Set<String> s = items.keySet();

                //checks to make sure all the items are there
                for(String key : s){
                    int a = players.get(owner).getAmountOfItem(key);
                    int b = items.get(key);
                    if(b > a){
                        currB.setBusinessStatus(false);
                        city.set(i, currB);
                    }
                }

                //another check to make sure you have money to pay workers
                if(city.get(i).isInBusiness()){
                    if(currP.getAmountOfMoney() < currB.getMoneyNeeded()){
                        currB.setBusinessStatus(false);
                        city.set(i, currB);
                    }else{
                        currP.removeMoney(currB.getMoneyNeeded());
                    }
                }

                //if the items are there than the items are reduced
                if(city.get(i).isInBusiness()){
                    for(String key : s){
                        int a = players.get(owner).getAmountOfItem(key);
                        int b = items.get(key);
                        currP.addItem(key, a-b);
                    }
                    //add money or items to player inventory
                    if(currB.getItemsProduced() == "GOLD"){
                        currP.addMoney(currB.getAmountProduced());
                    }else{
                        currP.addItem(currB.getItemsProduced(), currB.getAmountProduced()+currP.getAmountOfItem(currB.getItemsProduced()));
                    }
                    //set the temp player to the player
                    players.set(owner, currP);
                }

            }
        }

        return players;
    }

}