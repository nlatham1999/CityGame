package com.nicholaslatham.citygame;

import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class PlayerTurnActivity extends AppCompatActivity {

    private Button endTurn;
    private TextView whosTurn;
    private ArrayList<Players> players;
    private TextView gPlayers;
    private City mainCity;
    private TextView gCity;
    private int playerNum;
    private TabLayout topBar;
    private GameViewModel model;
    static TextView gPlayerNumTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_turn);

        getData();

        setUpGuiStuff();
    }

    public String getResult(){
        return "This is a test";
    }

    private void getData(){
        model = ViewModelProviders.of(this).get(GameViewModel.class);
//        players = model.getPlayers();
//        mainCity = model.getCity();
//        playerNum = players.size(); //this doesn't work for some reason. need to use the intent

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        playerNum = Integer.parseInt(bundle.getString("playerNumber"));
        players = (ArrayList<Players>) bundle.getSerializable("playerArray");
        mainCity = (City) bundle.getSerializable("cityArray");

        model.setPlayers(players);
        model.setCity(mainCity);
        model.setPlayerNum(playerNum);

    }

    private void setUpGuiStuff(){
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Select Your Favorite IDE");
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("PROFILE"));
        tabLayout.addTab(tabLayout.newTab().setText("BUY/SELL"));
        tabLayout.addTab(tabLayout.newTab().setText("VISIT MARKET"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        Intent intent = new Intent();
//        intent.putExtra("temp", "This is a test");

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapterPlayerTurn adapter = new PagerAdapterPlayerTurn(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }
        });

        endTurn = (Button) findViewById(R.id.PlayerTurnEndTurn);
        endTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                players = model.getPlayers();
                mainCity = model.getCity();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("playerArray", players);
                bundle.putSerializable("CityArray", mainCity);
                intent.putExtras(bundle);
                setResult(100, intent);
                finish();
            }
        });

        gPlayerNumTurn = (TextView) findViewById(R.id.PlayerTurnPlayerNum);
        setMoney();
    }

    public void setMoney(){
        gPlayerNumTurn.setText(players.get(playerNum).getName() + "'s turn" + "\t $" + String.valueOf(players.get(playerNum).getAmountOfMoney()));
    }
}
