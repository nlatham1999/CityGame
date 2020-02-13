package com.nicholaslatham.citygame;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.*;

public class MainGameActivity extends AppCompatActivity {

    private Button restart;
    private TextView gPlayerNumText;
    private TextView gPlayerAssets;
    private Button startRound;
    private int playerNum;
    private City mainCity;
    private ArrayList<Players> players;
    private ArrayList<String> stringNames;
    private int roundNumber = 1;   //keeps track of all the rounds
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        Intent intent = getIntent();
        stringNames = intent.getStringArrayListExtra("playerNames");
        playerNum = stringNames.size();

        setupPlayers(playerNum);

        setupGUI();

        mainGameLoop();

    }

    private void mainGameLoop(){

        displayPlayerAssets();


    }


    private void setupGUI(){
        //button for restarting the game
        restart = (Button) findViewById((R.id.startNewGame));
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        startRound = (Button) findViewById((R.id.startNewRound));
        startRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayerTurnActivity.class);
                count = 0;
                Bundle bundle = new Bundle();
                bundle.putString("playerNumber", String.valueOf(count));
                bundle.putSerializable("playerArray", players);
                bundle.putSerializable("CityArray", mainCity);
                intent.putExtras(bundle);
                startActivityForResult(intent, 100);
            }
        });

        gPlayerNumText = (TextView) findViewById(R.id.PlayerNumText2); //all purpose TextField for debugging
        gPlayerNumText.setText(String.valueOf(playerNum));

        gPlayerAssets = (TextView) findViewById((R.id.playerFinances));
    }


    private void setupPlayers(int playerNum){
        //set up all the player values
        mainCity = new City(10,4, 80, 5);
        players = new ArrayList<Players>();


        for(int i = 0; i < stringNames.size(); i++){
            Players tempPlayer;
            tempPlayer = new Players(10000, stringNames.get(i));
            players.add(tempPlayer);
        }

        GameViewModel model = ViewModelProviders.of(this).get(GameViewModel.class);
        model.setCity(mainCity);
        model.setPlayers(players);
    }

    //does all the stuff at the end of the round
    private void endOfRound(){
        for(int i = 0; i < players.size(); i++){
            mainCity.doFinances(players.get(i), i);
        }
        gPlayerNumText.setText(String.valueOf(playerNum) + " " + String.valueOf(roundNumber));
        displayPlayerAssets();
    }

    //displays the money that each player has
    private void displayPlayerAssets(){
        String playerInfo = "";

        for(int i = 0; i < players.size(); i++){
            playerInfo += players.get(i).getName() + " " + players.get(i).getAmountOfMoney() + "\n";
        }

        gPlayerAssets.setText(playerInfo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 69) {
            // Make sure the request was successful
                String temp = data.getStringExtra("newName");
                stringNames.add(temp);
        }else if(requestCode == 100){ //request code for end of the last player's turn and so the end of the round stuff is run
            Bundle bundle = data.getExtras();
            players = (ArrayList<Players>) bundle.getSerializable("playerArray");
            mainCity = (City) bundle.getSerializable("cityArray");
            count++;
            if(count < players.size()){
                Intent intent = new Intent(getApplicationContext(), PlayerTurnActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("playerNumber", String.valueOf(count));
                bundle2.putSerializable("playerArray", players);
                bundle2.putSerializable("CityArray", mainCity);
                intent.putExtras(bundle2);
                startActivityForResult(intent, 100);
            }else{
                roundNumber++;
                endOfRound();
            }
        }
    }
}
