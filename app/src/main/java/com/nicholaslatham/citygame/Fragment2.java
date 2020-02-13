package com.nicholaslatham.citygame;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private City mainCity;
    private int playerNum;
    private GameViewModel model;
    private ArrayList<Players> players;
    private RadioGroup gForSale;
    private RadioGroup gSellBusinessRadio;
    private Button gSellBusinessButton;
    private Button gBuyBusiness;
    private TextView temp;
    private ArrayList<Tuple<Integer, Business>> forSale;
    private ArrayList<Tuple<Integer,Business>> yourBusinessesNotForSale;
    private ArrayList<Tuple<Integer,Business>> yourBusinesses;
    private View rootView;
    private TextView gTextSlider;
    private SeekBar gPriceSlider;
    private int sellingPrice = 500;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_fragment2, container, false);

        //get the current data
        model = ViewModelProviders.of(getActivity()).get(GameViewModel.class);
        players = model.getPlayers();
        mainCity = model.getCity();
        playerNum = model.getNum();


        gForSale = (RadioGroup) rootView.findViewById(R.id.Fragment2ForSale);
        updateForSaleList();

        gBuyBusiness = (Button) rootView.findViewById(R.id.Fragment2ButtonBuyBusiness);
        BuyBusinessButton();


        gSellBusinessRadio = (RadioGroup) rootView.findViewById(R.id.Fragment2RadioSellBusiness);
        updateYourBusinesses();

        gSellBusinessButton = (Button) rootView.findViewById(R.id.Fragment2ButtonSellBusiness);
        SellBusinessButton();

        temp = (TextView) rootView.findViewById(R.id.Fragment2TextView1);
        temp.setText("Buying and selling houses and businesses gos here");

        gTextSlider = (TextView) rootView.findViewById(R.id.Fragment2TextViewPrice);

        gPriceSlider = (SeekBar) rootView.findViewById(R.id.Fragment2SliderPrice);
        setupSlider();

        return rootView;

    }

    private void updateYourBusinesses(){
        yourBusinesses = mainCity.getBusinessesOwnedByPlayerTuple(playerNum);
        for(int i = 0; i < yourBusinesses.size(); i++){
            System.out.println(yourBusinesses.get(i).x + " " + yourBusinesses.get(i).y.getType());
        }
        yourBusinessesNotForSale = new ArrayList<Tuple<Integer, Business>>();

        for(int i = 0; i < yourBusinesses.size(); i++){
            if(!yourBusinesses.get(i).y.isForSale()){
                yourBusinessesNotForSale.add(yourBusinesses.get(i));
            }
        }

        String list = "";
        RadioGroup.LayoutParams rprms;
        gSellBusinessRadio.removeAllViews();
        for(int i = 0; i < yourBusinessesNotForSale.size(); i++){
            list = yourBusinessesNotForSale.get(i).y.getType() +  "\n";
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(list);
            radioButton.setId(View.generateViewId());
            rprms= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            gSellBusinessRadio.addView(radioButton, rprms);
        }
        //gForSale.setText(list);
    }

    void setupSlider(){
        gPriceSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                sellingPrice = gPriceSlider.getProgress();
                gTextSlider.setText("$ "+ String.valueOf(sellingPrice));
            }
        });
    }

    private void SellBusinessButton(){
        gSellBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioButtonID = gSellBusinessRadio.getCheckedRadioButtonId();
                View radioButton = gSellBusinessRadio.findViewById(radioButtonID);
                int idx = gSellBusinessRadio.indexOfChild(radioButton);
                temp.setText(String.valueOf(idx));
                if(idx != -1){
                    mainCity.setPrice(yourBusinessesNotForSale.get(idx).x, sellingPrice);
                    mainCity.setForSale(yourBusinessesNotForSale.get(idx).x, true);
                    updateYourBusinesses();
                    updateForSaleList();
                }
            }
        });


    }

    //button for bying a business
    private void BuyBusinessButton(){
        gBuyBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioButtonID = gForSale.getCheckedRadioButtonId();
                View radioButton = gForSale.findViewById(radioButtonID);
                int idx = gForSale.indexOfChild(radioButton);
                temp.setText(String.valueOf(idx));
                if(idx != -1){
                    int seller = forSale.get(idx).y.getOwner();
                    int price = forSale.get(idx).y.getPrice();
                    if(players.get(playerNum).getAmountOfMoney() >= price) {
                        if (seller != -1) {  //is the buyer buying from a player or buying a new property
                            players.get(seller).addMoney(price);
                        }
                        players.get(playerNum).removeMoney(price);
                        mainCity.buyBusiness(playerNum, idx);
                        model.setPlayers(players);
                        model.setCity(mainCity);
                        updateForSaleList();
                        updateYourBusinesses();

                        PlayerTurnActivity activity = (PlayerTurnActivity) getActivity();
                        activity.setMoney();
                    }
                }
            }
        });
    }


    //adds radio buttons for each business for sale
    private void updateForSaleList(){
        forSale = mainCity.getBusinessesForSale();
        String list = "";
        RadioGroup.LayoutParams rprms;
        gForSale.removeAllViews();
        for(int i = 0; i < forSale.size(); i++){
            list = " $" + forSale.get(i).y.getPrice() + " " +  forSale.get(i).y.getType() +  "\n";
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(list);
            radioButton.setId(View.generateViewId());
            rprms= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            gForSale.addView(radioButton, rprms);
        }

        for(int i = 0; i < forSale.size(); i++){
            System.out.println(i + " " + forSale.get(i).x + " " + forSale.get(i).y.getType());
        }
        //gForSale.setText(list);
    }


}