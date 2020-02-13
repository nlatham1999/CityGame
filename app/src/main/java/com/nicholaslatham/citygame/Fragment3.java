package com.nicholaslatham.citygame;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.*;

public class Fragment3 extends Fragment {


    private City mainCity;
    private int playerNum;
    private GameViewModel model;
    private ArrayList<Players> players;
    private RadioGroup marketItems;
    private RadioGroup playerItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment3, container, false);

//        Intent intent = getActivity.getIntent();
//        String text = intent.getStringExtra("temp");

        model = ViewModelProviders.of(getActivity()).get(GameViewModel.class);
        players = model.getPlayers();
        mainCity = model.getCity();
        playerNum = model.getNum();

        marketItems = (RadioGroup) rootView.findViewById(R.id.fragment3RadioStuffForSale);
        setMarketItems();

        playerItems = (RadioGroup) rootView.findViewById(R.id.Fragment3RadioGroupPlayerItems);
        setPlayerItems();

        return rootView;

    }

    private void setMarketItems(){
        int numberOfTrades = mainCity.getNumberOfTradesMarket();

        String list = "";
        RadioGroup.LayoutParams rprms;
        marketItems.removeAllViews();
        for(int i = 0; i < numberOfTrades; i++){
            list = String.valueOf(mainCity.getPriceMarket(i)) + " " + mainCity.getItemMarket(i) + " " + String.valueOf(mainCity.getQuantityMarket(i)) +  "\n";
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(list);
            radioButton.setId(View.generateViewId());
            rprms= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            marketItems.addView(radioButton, rprms);
        }
    }

    private void setPlayerItems(){
        String list = "";

        Hashtable<String, Integer> items = players.get(playerNum).getItems();
        ArrayList<String> itemList = new ArrayList<String>();
        Set<String> s = items.keySet();
        for (String key : s) {
            itemList.add(key);
        }

        RadioGroup.LayoutParams rprms;
        playerItems.removeAllViews();
        for(int i = 0; i < itemList.size(); i++){
            list = itemList.get(i) + " " + String.valueOf(items.get(itemList.get(i))) + "\n";
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(list);
            radioButton.setId(View.generateViewId());
            rprms= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            playerItems.addView(radioButton, rprms);
        }
    }
}