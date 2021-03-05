package com.importtn.scoremyputtputt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EndScreen extends AppCompatActivity {
    Game gameObject;

    RecyclerView mRecyclerView;
    BoardAdapter mAdapter;
    Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_end_screen);
        Intent i = getIntent();
        gameObject = (Game)i.getSerializableExtra("gameObject");

        finishButton = findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finishUp();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.listPlayerObjects);
        mAdapter = new BoardAdapter(gameObject);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }

    private void finishUp(){
        Intent i = new Intent(this, Startup.class);
        startActivity(i);
    }

    public void onBackPressed(){
        finishUp();
    }
}
