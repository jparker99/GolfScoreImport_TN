package com.importtn.scoremyputtputt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PlayerDetails extends AppCompatActivity {
    Game gameObject;
    ImageButton bgn;
    private EditText mEditText;
    private Button mButton;
    private LinearLayout mLayout;
    private int numPlayers = 0;
    List<Player> players = new ArrayList<>();
    List<String> names = new ArrayList<>();

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

        mLayout = (LinearLayout) findViewById(R.id.linearLayout);
        mEditText = (EditText) findViewById(R.id.editText);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(onClick());
        TextView textView = new TextView(this);
    /*
    no
     */
    }
    private View.OnClickListener onClick() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLayout.addView(createNewTextView(mEditText.getText().toString()));

            }
        };
    }

    private TextView createNewTextView(String text) {
            final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final TextView textView = new TextView(this);
            textView.setLayoutParams(lparams);
            textView.setText(text);
            textView.setTextColor(Color.parseColor("#6A74F4"));

            names.add(text);
             mEditText.getText().clear();

            numPlayers++;


            return textView;
    }

    private String retrieveName(int id) {
        EditText editText = findViewById((id));

        System.out.println(editText.getText().toString());

        return editText.getText().toString();
    }

    private void finalizePlayers(){
        //Placeholder method, will need to have player names and icons later.
        if(!retrieveName(R.id.player1Name).equals("")){
            players.add(new Player(retrieveName(R.id.player1Name), ""));

        }
        if(!retrieveName(R.id.player2Name).equals("")) {
            players.add(new Player(retrieveName(R.id.player2Name), ""));
        }
        if(!retrieveName(R.id.player3Name).equals("")) {

            players.add(new Player(retrieveName(R.id.player3Name), ""));
        }
        if(!retrieveName(R.id.player4Name).equals("")) {

            players.add(new Player(retrieveName(R.id.player4Name), ""));
        }

        for(int i = 0; i<numPlayers; i++){
            players.add(new Player(names.get(i), ""));
        }

        gameObject.setPlayers(players);

        Intent i = new Intent(this, EnterScore.class);
        i.putExtra("gameObject", gameObject);
        finishAffinity();
        startActivity(i);
    }

}