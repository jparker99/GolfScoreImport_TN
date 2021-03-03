package com.importtn.scoremyputtputt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerDetails extends AppCompatActivity {
    Game gameObject;
    Button bgn;

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

    private void finalizePlayers(){
        //Placeholder method, will need to have player names and icons later.
        gameObject.addPlayer(new Player("Player 1", ""));
        gameObject.addPlayer(new Player("Player 2", ""));
        gameObject.addPlayer(new Player("Player 3", ""));
        gameObject.addPlayer(new Player("Player 4", ""));
        Intent i = new Intent(this, EnterScore.class);
        i.putExtra("gameObject", gameObject);
        startActivity(i);
    }
}
