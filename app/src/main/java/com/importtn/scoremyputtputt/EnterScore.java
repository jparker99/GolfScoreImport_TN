package com.importtn.scoremyputtputt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class EnterScore extends AppCompatActivity {
    Game gameObject;
    Player currentPlayer;
    List<Player> players;
    Iterator playerIterator;

    // Components
    TextView textHoleHeader;
    TextView displayPlayerName;
    Button nextHoleButton;
    Button nextPlayerButton;
    Button exitGameButton;
    Button incrementScore;
    Button decrementScore;
    EditText strokesDisplay;

    String p1_hole_ind;
    String p2_hole_ind;
    String finish_game_txt;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_enter_score);

        Intent i = getIntent();
        gameObject = (Game)i.getSerializableExtra("gameObject");


        textHoleHeader = findViewById(R.id.textHoleHeader);
        displayPlayerName = findViewById(R.id.playerNameDisplay);
        nextHoleButton = findViewById(R.id.nextHoleButton);
        nextPlayerButton = findViewById(R.id.nextPlayerButton);
        exitGameButton = findViewById(R.id.exitGameButton);
        incrementScore = findViewById(R.id.increaseStrokeButton);
        decrementScore = findViewById(R.id.reduceStrokeButton);
        strokesDisplay = findViewById(R.id.currentStrokeTextBox);

        nextHoleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                nextHole();
            }
        });

        nextPlayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                nextPlayer();
            }
        });

        exitGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                exitGame();
            }
        });

        incrementScore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                incrementStroke();
            }
        });

        decrementScore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                decrementStroke();
            }
        });

        p1_hole_ind = getResources().getString(R.string.p1_hole_ind);
        p2_hole_ind = getResources().getString(R.string.p2_hole_ind);
        finish_game_txt = getResources().getString(R.string.finish_gameTxt);

        players = gameObject.getPlayers();
        playerIterator = players.iterator();
        currentPlayer = (Player) playerIterator.next();

        textHoleHeader.setText(p1_hole_ind + " " + gameObject.getCurrentHole() + " " + p2_hole_ind);
        displayPlayerName.setText(currentPlayer.getName());
        strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole()-1]));


    }

    @SuppressLint("SetTextI18n")
    private void incrementStroke(){
        int[] scores = currentPlayer.getScores();
        scores[gameObject.getCurrentHole()-1]++;
        currentPlayer.setScores(scores);
        strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole()-1]));
    }

    @SuppressLint("SetTextI18n")
    private void decrementStroke(){
        int[] scores = currentPlayer.getScores();
        scores[gameObject.getCurrentHole()-1]--;
        currentPlayer.setScores(scores);
        strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole()-1]));
    }

    @SuppressLint("SetTextI18n")
    private void nextHole(){
        gameObject.setCurrentHole(gameObject.getCurrentHole() + 1);
        textHoleHeader.setText(p1_hole_ind + " " + gameObject.getCurrentHole() + " " + p2_hole_ind);
        playerIterator = gameObject.getPlayers().iterator();
        currentPlayer = (Player) playerIterator.next();
        displayPlayerName.setText(currentPlayer.getName());
        strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole()-1]));
    }

    @SuppressLint("SetTextI18n")
    private void nextPlayer(){
        if (!playerIterator.hasNext()) {
            playerIterator = players.iterator();
        }
        currentPlayer = (Player) playerIterator.next();
        displayPlayerName.setText(currentPlayer.getName());
        strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole()-1]));
    }

    private void exitGame(){

    }

}