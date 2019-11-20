import java.util.*;

public class CityGame{

    private static Scanner sc;
    private static String[] startItems = {"BREAD", "CANDLES", "IRON", "ROPE", "WAX", "MILK", "EGGS", "HEMP",
                                           "IRON TOOLS", "WOOD",  "WHEAT"}; //list of startItems
    private static int startItemAmount = 5; //amount of each item players get at the begining
    private static ArrayList<Players> players; //List of all the players
    //private static ArrayList<Business> city;  //List of all the businesses
    private static City city;
    private static int citySize = 10; // number of each type of businesses

    public static void main(String[] args) {

        gameSetup();
        mainGameLoop();
    }

    //sets up the game
    private static void gameSetup() {
        players = new ArrayList<Players>(); //initialize players
        sc = new Scanner(System.in); //initialize scanner


        System.out.println("how many people are playing?");
        int numberOfPlayers = sc.nextInt();
        sc.nextLine(); // clears the buffer

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("What is players " + i + " name?");
            String name = sc.nextLine();
            Players tempPlayer = new Players(1000, name);
            for (int j = 0; j < startItems.length; j++) {
                tempPlayer.addItem(startItems[j], startItemAmount);
            }
            players.add(tempPlayer);
        }

        city = new City(startItemAmount);
        
        City.setOwner(1, 3);
        City.setOwner(2, 4);
        City.setOwner(2, 5);

        city.printCity();
        System.out.println();
        //initBusinesses();
    }

    private static int mainGameLoop(){
        City.setOwner(1, 3);
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
            players = City.doFinances(players);
        }
    }
}