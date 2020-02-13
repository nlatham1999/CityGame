package com.nicholaslatham.citygame;

import java.util.*;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView gPlayerNumText;
    private SeekBar gPlayerNum;
    private Button gFinishButton;
    private int playerNum = 0;
    private boolean settingsDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gPlayerNumText = (TextView) findViewById(R.id.playerNumText);
        setupSlider();
        gFinishButton = (Button) findViewById((R.id.doneWithSettings));
        gFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsDone = true;
                Intent temp = new Intent(getApplicationContext(), GetNamesActivity.class);
                temp.putExtra("playerNumber", String.valueOf(playerNum));
                startActivity(temp);
                finish();
            }
        });
    }

    void setupSlider(){
        gPlayerNum = (SeekBar)findViewById(R.id.playerNumber); // make seekbar object
//        gPlayerNum.setOnSeekBarChangeListener((OnSeekBarChangeListener) this);
        gPlayerNum.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

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
                playerNum = gPlayerNum.getProgress();
                gPlayerNumText.setText("Number of Players: " + String.valueOf(playerNum));
            }
        });
    }

}
