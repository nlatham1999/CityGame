package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/26/2019.
 */

//handles all the stuff for the businesses, houses, and markets
//does not handle a lot of the player functions
//      the players are outside from the city looking in so it  makes sense
//      also makes sense in the event that there are multiple cities

import java.io.Serializable;
import java.util.*;

public class City implements Serializable {
    private static ArrayList<Business> businessTypes; //list of all the different types of  businesses;
    private static Hashtable<Integer, Business> cityBusinesses;  //table of all the businesses with an id associated with them
    private static Hashtable<Integer, ArrayList<Integer>> businessOwners; //table of all owners with a list of all business ids they own
    private static ArrayList<Tuple<Integer, Business>> businessesForSale; //list of all businesses for sale with their cooresponding id in cityBusinesses
    private static Hashtable<String, Integer> howManyOfEach; //keeps track of the amount of businesses in each indudtry
    private static Market market;


    private static int keyCount;
    public final int maxIndustries; //max number of industries that a person can invest in
    public final int maxPercentage; //max percebtage of a certain industry that a person can own
    public final int monopolyException; //exception to maxPercentage if the number of businesses in that industry are below this poitn

    City(int numberOfEachBusiness, int maxIndustries, int maxPercentage, int monopolyException) {
        keyCount = 0;
        cityBusinesses = new Hashtable<Integer, Business>();
        businessOwners = new Hashtable<Integer, ArrayList<Integer>>();
        howManyOfEach = new Hashtable<String, Integer>();

        this.maxIndustries = maxIndustries;
        this.maxPercentage = maxPercentage;
        this.monopolyException = monopolyException;

        market = new Market();

        makeListOfBusinessTypes();
        //createBusinesses(numberOfEachBusiness);



    }


    private static void makeListOfBusinessTypes() {
        businessTypes = new ArrayList<Business>();

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

        businessTypes.add(tempA);
        businessTypes.add(tempB);
        businessTypes.add(tempC);
        businessTypes.add(tempD);
        businessTypes.add(tempE);
        businessTypes.add(tempG);
        businessTypes.add(tempH);
        businessTypes.add(tempI);
        businessTypes.add(tempIS);
        businessTypes.add(tempL);
        businessTypes.add(tempP);
        businessTypes.add(tempW);

    }

    //returns a list of all businesses for sale
    public static ArrayList<Tuple<Integer, Business>> getBusinessesForSale() {
        compileListOfAllBusinessesForSale();
        return businessesForSale;
    }

    public static ArrayList<Business> getBusinessTypes() {
        return businessTypes;
    }

    //puts together a list of all the businesses for sale
    private static void compileListOfAllBusinessesForSale() {
        businessesForSale = new ArrayList<Tuple<Integer, Business>>();

        //add the list of business types
        for (int i = 0; i < businessTypes.size(); i++) {
            Business tempBusiness = businessTypes.get(i);
            Tuple<Integer, Business> tempTuple = new Tuple(-1, tempBusiness);
            businessesForSale.add(tempTuple);
        }

        //add all businesses that are for sale
        Set<Integer> s = cityBusinesses.keySet();
        for (int i = 0; i < s.size(); i++) {
            if (cityBusinesses.get(i).getOwner() >= 0 && cityBusinesses.get(i).isForSale()) {
                Tuple<Integer, Business> tempTuple = new Tuple(i, cityBusinesses.get(i));
                businessesForSale.add(tempTuple);
            }
        }
    }

    //checks to make sure that the purchase of a property doesnt go against monopoly rules
    //true: okay
    //false: breaks rules
    public static boolean checkMonopolyRules(int owner, int businessPosition) {
        //first make sure the player isnt diversifying too much

        //second make sure that the player isnt monopoplyzing in the industry
        return true;
    }

    //get the business being sold and transfers it to the new owner
    //does not handle the actual gold transaction between players
    //  that can be done in the main - get list of for sales and get the owner and transfer money
    // return 0: success
    // return -1: broke monopoly rules
    public static int buyBusiness(int owner, int businessPosition) {
        System.out.println("City-BuyBusiness" + businessPosition + " " + businessesForSale.size());
        Tuple<Integer, Business> tempTuple = businessesForSale.get(businessPosition);
        int id = tempTuple.x;

        if (!checkMonopolyRules(owner, businessPosition)) {
            return -1;
        }

        // is this a new Business?
        if (id == -1) {
            Business tempBusiness = tempTuple.y;
            tempBusiness.setSaleStatus(false);
            tempBusiness.setOwner(owner);
            if (businessOwners.containsKey(owner)) {
                ArrayList<Integer> tempList = businessOwners.get(owner);
                tempList.add(keyCount);
                businessOwners.put(owner, tempList);
            } else {
                ArrayList<Integer> tempList = new ArrayList<Integer>();
                tempList.add(keyCount);
                businessOwners.put(owner, tempList);
            }
            cityBusinesses.put(keyCount, tempBusiness);
            keyCount++;

            String itemProd = tempBusiness.getItemsProduced();
            int currentInIndustry;
            if (howManyOfEach.containsKey(itemProd)) {
                currentInIndustry = howManyOfEach.get(itemProd) + 1;
            } else {
                currentInIndustry = 1;
              }
            howManyOfEach.put(itemProd, currentInIndustry);
        } else {
            setOwner(owner, id);
        }
        makeListOfBusinessTypes();
        return 0;

    }

    //sets the owner of a business
    private static void setOwner(int owner, int id) {
        //get the business at the id and get the current id
        Business tempBusiness = cityBusinesses.get(id);
        int currentOwner = tempBusiness.getOwner();

        //remove the id from the current Owners list of businesses;
        if (businessOwners.containsKey(currentOwner)) {
            ArrayList<Integer> tempList = businessOwners.get(currentOwner);
            if (tempList.contains(id)) {
                tempList.remove((Integer) id);
                businessOwners.put(currentOwner, tempList);
            }
        }

        //put the id into the new owners list of businesses
        if (businessOwners.containsKey(owner)) {
            ArrayList<Integer> tempList = businessOwners.get(owner);
            if (!tempList.contains(id)) {
                tempList.add(id);
                businessOwners.put(owner, tempList);
            }
        } else {
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            tempList.add(id);
            businessOwners.put(owner, tempList);
        }

        //update the owner in the business object in cityBusinesses
        tempBusiness.setSaleStatus(false);
        tempBusiness.setOwner(owner);
        cityBusinesses.put(id, tempBusiness);
    }

    private static void createBusinesses(int start) {
        for (int i = 0; i < start; i++) {
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

            cityBusinesses.put(keyCount, tempA);
            keyCount++;
            cityBusinesses.put(keyCount, tempB);
            keyCount++;
            cityBusinesses.put(keyCount, tempC);
            keyCount++;
            cityBusinesses.put(keyCount, tempD);
            keyCount++;
            cityBusinesses.put(keyCount, tempE);
            keyCount++;
            cityBusinesses.put(keyCount, tempG);
            keyCount++;
            cityBusinesses.put(keyCount, tempH);
            keyCount++;
            cityBusinesses.put(keyCount, tempI);
            keyCount++;
            cityBusinesses.put(keyCount, tempIS);
            keyCount++;
            cityBusinesses.put(keyCount, tempL);
            keyCount++;
            cityBusinesses.put(keyCount, tempP);
            keyCount++;
            cityBusinesses.put(keyCount, tempW);
            keyCount++;
        }
    }

    //does the finances for a player
    public static Players doFinances(Players playerObj, int playerNum) {

        //if the player doesnt own any businesses then we are done
        if (!businessOwners.containsKey(playerNum)) {
            return playerObj;
        }

        ArrayList<Integer> tempList = businessOwners.get(playerNum);

        boolean isInBusiness = true;

        //loops through all the business that the player owns
        for (int i = 0; i < tempList.size(); i++) {
            int id = tempList.get(i);
            Business tempBusiness = cityBusinesses.get(id);

            Hashtable<String, Integer> items = tempBusiness.getItemsNeeded();
            Set<String> s = items.keySet();

            //check to make sure the player has the neccessary items
            for (String key : s) {
                int a = playerObj.getAmountOfItem(key);  //how much of a certain item the player has
                int b = items.get(key); //how much of a certain item is needed
                if (b > a) {
                    isInBusiness = false;
                }
            }

            //another check to make sure you have money to pay workers
            if (isInBusiness) {
                if (playerObj.getAmountOfMoney() < tempBusiness.getMoneyNeeded()) {
                    isInBusiness = false;
                } else {
                    playerObj.removeMoney(tempBusiness.getMoneyNeeded());
                }
            }

            //if the items are there than the items are reduced
            if (isInBusiness) {
                for (String key : s) {
                    int a = playerObj.getAmountOfItem(key);
                    int b = items.get(key);
                    playerObj.addItem(key, a - b);
                }
                //add money or items to player inventory
                if (tempBusiness.getItemsProduced() == "GOLD") {
                    playerObj.addMoney(tempBusiness.getAmountProduced());
                } else {
                    playerObj.addItem(tempBusiness.getItemsProduced(), tempBusiness.getAmountProduced() + playerObj.getAmountOfItem(tempBusiness.getItemsProduced()));
                }

            }

            tempBusiness.setBusinessStatus(isInBusiness);
            cityBusinesses.put(id, tempBusiness);
        }

        return playerObj;
    }

    public static ArrayList<Business> getBusinessesOwnedByPlayer(int player) {
        ArrayList<Business> temp = new ArrayList<Business>();
        Set<Integer> s = cityBusinesses.keySet();
        for (int i = 0; i < s.size(); i++) {
            if (cityBusinesses.get(i).getOwner() == player) {
                temp.add(cityBusinesses.get(i));
            }
        }

        return temp;
    }

    public static ArrayList<Tuple<Integer, Business>> getBusinessesOwnedByPlayerTuple(int player){
        ArrayList<Tuple<Integer,Business>> temp = new ArrayList<Tuple<Integer,Business>>();
        Set<Integer> s = cityBusinesses.keySet();
        for (int i = 0; i < s.size(); i++) {
            if (cityBusinesses.get(i).getOwner() == player) {
                Tuple t = new Tuple(i, cityBusinesses.get(i));
                temp.add(t);
            }
        }

        return temp;
    }

    public static void setForSale(int id, boolean forSale){
        if(cityBusinesses.containsKey(id)) {
            Business temp = cityBusinesses.get(id);
            temp.setSaleStatus(forSale);
            cityBusinesses.put(id, temp);
        }
    }

    public static void setPrice(int id, int price){
        Business temp = cityBusinesses.get(id);
        temp.setPrice(price);
        cityBusinesses.put(id, temp);
    }


    /*
    Below are wrappers for the market class. Instead of creating a Market object
    in the frontent like i did with City and Players, I created the object in City.
    This way data updates happen with the City and so there is less to keep track of.
    Like with most of the business methods, Players objects dont get passed into the methods.
    All transactions take place in the frontend an the backend only handles inventory
     */

    //wrapper
    public static int getNumberOfTradesMarket(){
        return market.numberOfTrades();
    }

    //wrapper
    public static void addTradeMarket(int itemPrice, String itemType, int itemQuantity, int player){
        market.addTrade(itemPrice, itemType, itemQuantity, player);
    }

    //wrapper
    public static void removeTradeMarket(int tradeNum){
        market.removeTrade(tradeNum);
    }

    //wrapper
    public static int getPriceMarket(int tradeNum){
        return market.getPrice(tradeNum);
    }

    //wrapper
    public static int getQuantityMarket(int tradeNum){
        return market.getQuantity(tradeNum);
    }

    //wrapper
    public static String getItemMarket(int tradeNum){
        return market.getItem(tradeNum);
    }

    //wrapper
    public int getSellerMarket(int tradeNum){
        return market.getSeller(tradeNum);
    }

}