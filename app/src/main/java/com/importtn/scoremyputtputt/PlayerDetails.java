package com.importtn.scoremyputtputt;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerDetails extends AppCompatActivity {
    Game gameObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_player_details);
        Intent i = getIntent();
        gameObject = (Game)i.getSerializableExtra("gameObject");
    }
}
