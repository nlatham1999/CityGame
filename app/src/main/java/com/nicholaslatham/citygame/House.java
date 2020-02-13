package com.nicholaslatham.citygame;

/**
 * Created by Nicholas Latham on 11/28/2019.
 */

import java.util.*;

public class House {
    private static int maxResidents;
    private static int residents;
    private static int owner;

    House(int houseOwner){
        owner = houseOwner;
        maxResidents = 5;
        residents = 0;
    }

    public static void addResidents(int num){
        if(num > 0){
            residents += num;
            if(residents > maxResidents){
                residents = maxResidents;
            }
        }

    }

    public static int getMaxResidents(){
        return maxResidents;
    }

    public static int getResidents(){
        return residents;
    }

    public static int getOwner(){
        return owner;
    }
}
