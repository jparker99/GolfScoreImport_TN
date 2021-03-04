package com.importtn.scoremyputtputt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PlayerDetails extends AppCompatActivity {
    Game gameObject;
    ImageButton bgn;

    List<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_player_details);

        bgn = findViewById(R.id.placeHolderStGameButton);

        Intent i = getIntent();
        gameObject = (Game)i.getSerializableExtra("gameObject");

        bgn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                finalizePlayers();
            }
        });

    }

    private String retrieveName(int id) {
        EditText editText = findViewById((id));

        System.out.println(editText.getText().toString());

        return editText.getText().toString();
    }

    private void finalizePlayers(){
        //Placeholder method, will need to have player names and icons later.
        players.add(new Player(retrieveName(R.id.player1Name), ""));
        players.add(new Player(retrieveName(R.id.player2Name), ""));
        players.add(new Player(retrieveName(R.id.player3Name), ""));
        players.add(new Player(retrieveName(R.id.player4Name), ""));

        gameObject.setPlayers(players);

        Intent i = new Intent(this, EnterScore.class);
        i.putExtra("gameObject", gameObject);
        finishAffinity();
        startActivity(i);
    }

}
