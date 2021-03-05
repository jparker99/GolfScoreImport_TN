package com.importtn.scoremyputtputt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class EnterScore extends AppCompatActivity {
    private enum ActionEnum{
        hole,
        player
    }

    Game gameObject;
    Player currentPlayer;
    List<Player> players;
    ListIterator<Player> playerIterator;

    Stack<ActionEnum> actionHistory = new Stack<>();
    Stack<ListIterator<Player>> iteratorHistory = new Stack<>();
    Stack<Player> currPlayerHistory = new Stack<>();

    // Components
    TextView textHoleHeader;
    TextView displayPlayerName;
    Button nextHoleButton;
    Button nextPlayerButton;
    Button exitGameButton;
    Button incrementScore;
    Button decrementScore;
    Button overviewButton;
    EditText strokesDisplay;

    String p1_hole_ind;
    String p2_hole_ind;
    String finish_game_txt;
    String exit_game_txt;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_enter_score);

        Intent i = getIntent();
        gameObject = (Game) i.getSerializableExtra("gameObject");
        currentPlayer = (Player) i.getSerializableExtra("currentPlayer");


        textHoleHeader = findViewById(R.id.textHoleHeader);
        displayPlayerName = findViewById(R.id.playerNameDisplay);
        nextHoleButton = findViewById(R.id.nextHoleButton);
        nextPlayerButton = findViewById(R.id.nextPlayerButton);
        exitGameButton = findViewById(R.id.exitGameButton);
        incrementScore = findViewById(R.id.tempIncreasePlayers);
        decrementScore = findViewById(R.id.tempReducePlayers);
        strokesDisplay = findViewById(R.id.tempNumberPlayers);
        overviewButton = findViewById(R.id.overviewButton);

        nextHoleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextHole();
            }
        });

        nextPlayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextPlayer();
            }
        });

        exitGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                exitGame();
            }
        });

        incrementScore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                incrementStroke();
            }
        });

        decrementScore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                decrementStroke();
            }
        });

        overviewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goOverview();
            }
        });

        p1_hole_ind = getResources().getString(R.string.p1_hole_ind);
        p2_hole_ind = getResources().getString(R.string.p2_hole_ind);
        finish_game_txt = getResources().getString(R.string.finish_gameTxt);
        exit_game_txt = getResources().getString(R.string.exit_gameTxt);

        players = gameObject.getPlayers();
        playerIterator = players.listIterator();
        if (currentPlayer == null) {
            currentPlayer = playerIterator.next();
        } else {
           boolean t = true;
           while(t){
               Player tmp = playerIterator.next();
               if(tmp.getName().equals(currentPlayer.getName())){
                   t = false;
               }
           }
        }
        textHoleHeader.setText(p1_hole_ind + " " + gameObject.getCurrentHole() + " " + p2_hole_ind);
        displayPlayerName.setText(currentPlayer.getName());
        strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole() - 1]));


    }

    @SuppressLint("SetTextI18n")
    private void incrementStroke() {
        int[] scores = currentPlayer.getScores();
        scores[gameObject.getCurrentHole() - 1]++;
        currentPlayer.setScores(scores);
        strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole() - 1]));
    }

    @SuppressLint("SetTextI18n")
    private void decrementStroke() {
        int[] scores = currentPlayer.getScores();
        if (scores[gameObject.getCurrentHole() - 1] > 0) {
            scores[gameObject.getCurrentHole() - 1]--;
            currentPlayer.setScores(scores);
            strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole() - 1]));
        }
    }

    @SuppressLint("SetTextI18n")
    private void nextHole() {
        if (gameObject.getCurrentHole() < 17) {
            gameObject.setCurrentHole(gameObject.getCurrentHole() + 1);
            textHoleHeader.setText(p1_hole_ind + " " + gameObject.getCurrentHole() + " " + p2_hole_ind);
            iteratorHistory.push(playerIterator);
            currPlayerHistory.push(currentPlayer);
            playerIterator = players.listIterator();
            currentPlayer = playerIterator.next();
            displayPlayerName.setText(currentPlayer.getName());
            strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole() - 1]));
        } else {
            gameObject.setCurrentHole(gameObject.getCurrentHole() + 1);
            textHoleHeader.setText(p1_hole_ind + " " + gameObject.getCurrentHole() + " " + p2_hole_ind);
            iteratorHistory.push(playerIterator);
            currPlayerHistory.push(currentPlayer);
            playerIterator = players.listIterator();
            currentPlayer = playerIterator.next();
            displayPlayerName.setText(currentPlayer.getName());
            strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole() - 1]));
            exitGameButton.setText(finish_game_txt);
            nextHoleButton.setVisibility(View.INVISIBLE);
        }
        actionHistory.push(ActionEnum.hole);
    }

    @SuppressLint("SetTextI18n")
    private void nextPlayer() {
        System.out.println(currentPlayer.getName());
        if (!playerIterator.hasNext()) {
            playerIterator = players.listIterator();
        }
        currentPlayer = playerIterator.next();
        displayPlayerName.setText(currentPlayer.getName());
        strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole() - 1]));
        actionHistory.push(ActionEnum.player);
    }

    private void exitGame() {
        Intent i = new Intent(this, EndScreen.class);
        i.putExtra("gameObject", gameObject);
        startActivity(i);
    }

    private void goOverview() {
        Intent i = new Intent(this, Overview.class);
        i.putExtra("gameObject", gameObject);
        i.putExtra("currentPlayer", currentPlayer);
        startActivity(i);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBackPressed(){
        if(actionHistory.empty()){
            Game newGame = new Game();
            Intent i = new Intent(this, PlayerDetails.class);
            i.putExtra("gameObject", newGame);
            startActivity(i);
            return;
        }
        ActionEnum lastAction = actionHistory.pop();
        switch(lastAction){
            case hole:
                if (gameObject.getCurrentHole() < 18) {
                    gameObject.setCurrentHole(gameObject.getCurrentHole() - 1);
                    textHoleHeader.setText(p1_hole_ind + " " + gameObject.getCurrentHole() + " " + p2_hole_ind);
                    playerIterator = iteratorHistory.pop();
                    currentPlayer = currPlayerHistory.pop();
                    displayPlayerName.setText(currentPlayer.getName());
                    strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole() - 1]));
                } else {
                    gameObject.setCurrentHole(gameObject.getCurrentHole() - 1);
                    textHoleHeader.setText(p1_hole_ind + " " + gameObject.getCurrentHole() + " " + p2_hole_ind);
                    playerIterator = iteratorHistory.pop();
                    currentPlayer = currPlayerHistory.pop();
                    displayPlayerName.setText(currentPlayer.getName());
                    strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole() - 1]));
                    exitGameButton.setText(exit_game_txt);
                    nextHoleButton.setVisibility(View.VISIBLE);
                }
                break;
            case player:
                playerIterator.previous();
                if (!playerIterator.hasPrevious()) {
                    playerIterator = players.listIterator(players.size()-1);
                    currentPlayer = playerIterator.next();
                } else {
                    currentPlayer = playerIterator.previous();
                    playerIterator.next();
                }
                displayPlayerName.setText(currentPlayer.getName());
                strokesDisplay.setText(Integer.toString(currentPlayer.getScores()[gameObject.getCurrentHole() - 1]));
                break;
        }
    }

}