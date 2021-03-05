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

    private String retrieveName(int id, int counter) {
        EditText editText = findViewById((id));

        String name = editText.getText().toString();

        return name.isEmpty() ? "Player " + counter : name;
    }

    private void finalizePlayers(){
        //Placeholder method, will need to have player names and icons later.
        int counter = 1;
        players.add(new Player(retrieveName(R.id.player1Name, counter++), ""));
        players.add(new Player(retrieveName(R.id.player2Name, counter++), ""));
        players.add(new Player(retrieveName(R.id.player3Name, counter++), ""));
        players.add(new Player(retrieveName(R.id.player4Name, counter), ""));

        gameObject.setPlayers(players);

        Intent i = new Intent(this, EnterScore.class);
        i.putExtra("gameObject", gameObject);
        startActivity(i);
    }
    @Override
    public void onBackPressed(){
        Intent i = new Intent(this, Startup.class);
        startActivity(i);
    }

}
