package com.nicholaslatham.citygame;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class Fragment1 extends Fragment {

    private RadioGroup gOwnedBusinesses;
    private ArrayList<Players> players;
    private City mainCity;
    private int playerNum;
    private TextView gListOfItems;
    private TextView gBusinessDetails;
    private ArrayList<Business> businesses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment1, container, false);

        GameViewModel model = ViewModelProviders.of(getActivity()).get(GameViewModel.class);
        players = model.getPlayers();
        mainCity = model.getCity();
        playerNum = model.getNum();

        TextView temp = (TextView) rootView.findViewById(R.id.Fragment1TextView1);
        temp.setText("Player Profile goes here");

        gOwnedBusinesses = (RadioGroup) rootView.findViewById((R.id.Fragment1BusinessesRadio));
        setgOwnedBusinesses();

        gListOfItems = (TextView) rootView.findViewById(R.id.Fragment1ListOfItemsTextView);
        setItemList();

        gBusinessDetails = (TextView) rootView.findViewById(R.id.Fragment1BusinessDetailsTextView);

        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        setgOwnedBusinesses();
    }

    @Override
    public void onPause(){
        super.onPause();
        super.onDestroy();
    }

    private void setItemList(){
        String list = "";

        Hashtable<String, Integer> items = players.get(playerNum).getItems();
        Set<String> s = items.keySet();
        for (String key : s) {
            list += key + ": " + String.valueOf(items.get(key)) + "\n";
        }

        gListOfItems.setText(list);

    }


    public void setgOwnedBusinesses(){
        String list = "";
        businesses = mainCity.getBusinessesOwnedByPlayer(playerNum);
        RadioGroup.LayoutParams rprms;
        gOwnedBusinesses.removeAllViews();
        for(int i = 0; i < businesses.size(); i++){
            list = businesses.get(i).getType();
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(list);
            radioButton.setId(View.generateViewId());
            rprms= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            gOwnedBusinesses.addView(radioButton, rprms);
        }

        gOwnedBusinesses.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                View radioButton = gOwnedBusinesses.findViewById(checkedId);
                int idx = gOwnedBusinesses.indexOfChild(radioButton);
                String temp = "";
                temp += "TYPE: " + businesses.get(idx).getType() + "\n";
                temp += "ITEM PRODUCED: " + businesses.get(idx).getItemsProduced() + "\n";
                temp += "AMOUNT PRODUCED: " + String.valueOf(businesses.get(idx).getAmountProduced()) + "\n";
                temp += "ITEMS NEEDED: \n";
                Hashtable<String, Integer> items = businesses.get(idx).getItemsNeeded();
                Set<String> s = items.keySet();
                for (String key : s) {
                    temp += "\t" + String.valueOf(items.get(key)) + " " + key + "\n";
                }
                temp += "MONEY NEEDED: " + String.valueOf(businesses.get(idx).getMoneyNeeded());
                gBusinessDetails.setText(temp);

            }
        });
    }


}