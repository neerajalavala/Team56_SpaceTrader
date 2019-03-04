package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.gamelogic.Player;

import com.example.spacetrader.entity.world.Planet;

import java.util.List;

public class ViewPlanet extends AppCompatActivity {
    public static final String PLAYER_DATA = "PLAYER_DATA";

    /** an int for the request code */
    private static final int EDIT_REQUEST = 5;

    private Planet planet;

    private TextView planet_name;

    private TextView tech_lev;
    private TextView resources;

    private Button trade_buy;
    private Button trade_sell;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_planet);

        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        planet_name = findViewById(R.id.planetName);
        tech_lev = findViewById(R.id.tech_level);
        resources = findViewById(R.id.resources);

        planet = (Planet) getIntent().getSerializableExtra(PLAYER_DATA);

        planet_name.setText(planet.getName());
        tech_lev.setText(planet.getTechLevel().toString());
        resources.setText(planet.getResources().toString());

        setTitle("Buy or Sell with " + planet.getName());

        trade_buy = findViewById(R.id.buy_button);
        trade_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPlanet.this, TradeScreenBuy.class);
                intent.putExtra(PLAYER_DATA, planet.getMarketPlace());
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });

        trade_sell = findViewById(R.id.sell_button);
        trade_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPlanet.this, TradeScreenSell.class);
                intent.putExtra(PLAYER_DATA, planet);
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }
}
