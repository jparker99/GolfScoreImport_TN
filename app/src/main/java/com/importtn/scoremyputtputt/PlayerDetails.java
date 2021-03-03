package com.importtn.scoremyputtputt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerDetails extends AppCompatActivity {
    Game gameObject;
    Button bgn;
    Button incrementPlayers;
    Button decrementPlayers;
    EditText numberPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_player_details);

        bgn = findViewById(R.id.placeHolderStGameButton);
        incrementPlayers = findViewById(R.id.tempIncreasePlayers);
        decrementPlayers = findViewById(R.id.tempReducePlayers);
        numberPlayers = findViewById(R.id.tempNumberPlayers);


        Intent i = getIntent();
        gameObject = (Game)i.getSerializableExtra("gameObject");

        bgn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                int number = Integer.parseInt(numberPlayers.getText().toString());
                finalizePlayers(number);
            }
        });

        incrementPlayers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                incrementPlayers();
            }
        });
        decrementPlayers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                decrementPlayers();
            }
        });
    }

    private void finalizePlayers(int number){
        //Placeholder method, will need to have player names and icons later.
        for(int i = 1; i <= number; i++){
            gameObject.addPlayer(new Player("Player " + i, ""));
        }

        Intent i = new Intent(this, EnterScore.class);
        i.putExtra("gameObject", gameObject);
        startActivity(i);
    }

    @SuppressLint("SetTextI18n")
    private void incrementPlayers(){
        int currPlayers = Integer.parseInt(numberPlayers.getText().toString());
        currPlayers++;
        numberPlayers.setText(Integer.toString(currPlayers));
    }

    @SuppressLint("SetTextI18n")
    private void decrementPlayers(){

        int currPlayers = Integer.parseInt(numberPlayers.getText().toString());
        if(currPlayers > 1){
            currPlayers--;
            numberPlayers.setText(Integer.toString(currPlayers));
        }

    }
}
