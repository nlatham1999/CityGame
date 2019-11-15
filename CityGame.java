import java.util.*;

public class CityGame{

    private static Scanner sc;
    private static String[] startItems = {"BREAD", "CANDLES", "IRON", "ROPE"}; //list of startItems
    private static int startItemAmount = 5; //amount of each item players get at the begining
    private static ArrayList<Players> players; //List of all the players
    private static ArrayList<Business> city;  //List of all the businesses
    private static int citySize = 10;  //number of each type  of businesses
    public static void main(String[] args){

        gameSetup();
        mainGameLoop();
    }

    private static void gameSetup(){
        players = new ArrayList<Players>();
        sc = new Scanner(System.in);
        System.out.println("test");
        sc.nextLine();
        System.out.println("how many people are playing?");
        int numberOfPlayers = sc.nextInt();
        sc.nextLine(); //clears the buffer

        for(int i = 0; i < numberOfPlayers; i++){
            System.out.println("What is players " + i + " name?");
            String name = sc.nextLine();
            Players tempPlayer = new Players(1000, name);
            for(int j = 0; j < startItems.length; j++){
                tempPlayer.addItem(startItems[j], startItemAmount);
            }
            players.add(tempPlayer);
        }

        initBusinesses();
    }

    private static int mainGameLoop(){
        System.out.println();
        while(true){
            for(int i = 0; i < players.size(); i++){
                Players tempPlayer = players.get(i);

                System.out.println(tempPlayer.getName());
                System.out.println("Gold:" + tempPlayer.getAmountOfMoney());
                System.out.println("Items:" + tempPlayer.getItems());

                String input = sc.nextLine();
                if(input.contains("exit")){
                    return 1;
                }

                players.set(i, tempPlayer);
            }
        }
    }

    private static void initBusinesses(){
        city = new ArrayList<Business>();
        for(int i = 0; i < citySize; i++){
            GoldMine tempGM = new GoldMine("NONE");
            city.add(tempGM);
        }
        for(int i = 0; i < city.size(); i++){
            System.out.println(city.get(i).getOwner() + " " + city.get(i).getType());
        }
    }
}