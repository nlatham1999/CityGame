package com.nicholaslatham.citygame;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

//simple activity to get the name of a player

public class GetNamesActivity extends AppCompatActivity {

    private TextInputEditText input;
    private TextView enterText;
    private Button done;
    private ArrayList<String> names;
    private int playerNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_names);

        Intent intentTemp = getIntent();
        playerNum = Integer.parseInt(intentTemp.getStringExtra("playerNumber"));

        names = new ArrayList<String>();

        input = (TextInputEditText) findViewById(R.id.playerNameInput);
        done = (Button) findViewById(R.id.doneEnteringName);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = input.getText().toString();
                names.add(name);

                if(names.size() == playerNum-1){
                    done.setText("SAVE AND CONTINUE");
                    input.setText("Player " + String.valueOf(names.size()+1));
                }else if(names.size() >= playerNum){
                    Intent intent = new Intent(getApplicationContext(), MainGameActivity.class);
                    intent.putExtra("playerNames", names);
                    startActivity(intent);
                    finish();
                }else{
                    done.setText("SAVE AND INPUT NEXT NAME");
                    input.setText("Player " + String.valueOf(names.size()+1));
                }
            }
        });



    }
}
