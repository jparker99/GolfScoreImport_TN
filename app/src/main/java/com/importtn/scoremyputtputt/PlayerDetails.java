package com.importtn.scoremyputtputt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
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

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                //mLayout.addView(createNewTextView(mEditText.getText().toString()));
//                if (mEditText.getText().toString().equals("")) {
//                    Toast.makeText(getApplication(), "No blank players",
//                            Toast.LENGTH_LONG).show();
//                } else{


                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row, null);
                TextView playerText = (TextView) addView.findViewById(R.id.textout);
                playerText.setText(mEditText.getText().toString());
                names.add(mEditText.getText().toString());


                mLayout.addView(addView);
                mEditText.getText().clear();

                Button buttonRemove = (Button) addView.findViewById(R.id.remove);

                buttonRemove.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ((LinearLayout) addView.getParent()).removeView(addView);
                    }
                });
            }

            //}
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

    private String retrieveName(int id, int counter) {
        EditText editText = findViewById((id));

        String name = editText.getText().toString();

        return name.isEmpty() ? "Player " + counter : name;
    }

    private void finalizePlayers(){
        //Placeholder method, will need to have player names and icons later.

        for(int i = 0; i<names.size(); i++){
            players.add(new Player(names.get(i), ""));
        }

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