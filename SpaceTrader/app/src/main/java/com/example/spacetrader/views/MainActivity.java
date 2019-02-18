package com.example.spacetrader.views;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;

import com.example.spacetrader.R;

/**
 * This is the startup activity.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button view_players = findViewById(R.id.view_players_button);
        view_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewAllPlayersActivity.class);
                System.out.print("hi");
                startActivity(intent);
            }
        });

        Button add_player = findViewById(R.id.add_player_button);
        add_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreatePlayerActivity.class);
                startActivity(intent);
            }
        });
    }

}
