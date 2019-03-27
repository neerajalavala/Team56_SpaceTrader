package com.example.spacetrader.views;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.commerce.MarketPlace;
import com.example.spacetrader.entity.gamelogic.Player;
import com.example.spacetrader.entity.world.Planet;
import com.example.spacetrader.entity.world.SolarSystem;
import com.example.spacetrader.entity.world.Universe;
import com.example.spacetrader.viewmodels.PlayerListingViewModel;

import android.view.MenuItem;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import android.support.design.widget.BottomNavigationView;

public class GameStartScreen extends AppCompatActivity {
    public static final String PLAYER_DATA = "PLAYER_DATA";

    private String new_market = "UPDATED_MARKET";

    /** an int for the request code */
    private static final int EDIT_REQUEST = 5;

    private List<Player> players;

    private Player player;

    private PlayerListingViewModel viewModel;

    private Universe game;
    private SolarSystem currentSystem;
    private Planet currentPlanet;


    private FrameLayout placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_layout);

        FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        this.player = (Player) getIntent().getSerializableExtra(PLAYER_DATA);

        this.viewModel = ViewModelProviders.of(this).get(PlayerListingViewModel.class);
        this.players = viewModel.getPlayers();

        planetFrag pFrag = planetFrag.newInstance(player);

        transaction.add(R.id.placeholder_planet, pFrag, "planet");
        transaction.commit();

        placeholder = findViewById(R.id.placeholder_planet);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    Fragment in;

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        players = viewModel.getPlayers();
                        for (int x = 0; x < players.size(); x++){
                            if (players.get(x).getID() == player.getID()){
                                GameStartScreen.this.player = players.get(x);
                            }
                        }


                        final FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

                        switch (item.getItemId()) {

                            case R.id.action_planet:
                                planetFrag pFrag = planetFrag.newInstance(player);
                                trans.replace(R.id.placeholder_planet, pFrag, "planet");
                                trans.commit();
                                break;

                            case R.id.action_buy:

                                System.out.println();
                                System.out.println(player.getCredits().toString());
                                System.out.println();

                                buyFrag bFrag = buyFrag.newInstance(player);
                                trans.replace(R.id.placeholder_planet, bFrag, "buy");
                                trans.commit();
                                break;

                            case R.id.action_sell:

                                sellFrag sFrag = new sellFrag().newInstance(player);
                                trans.replace(R.id.placeholder_planet, sFrag, "sell");
                                trans.commit();
                                break;

                            case R.id.action_shipyard:

                                shipFrag shipFrag = new shipFrag();
                                trans.replace(R.id.placeholder_planet, shipFrag, "ship");
                                trans.commit();
                                break;

                            case R.id.action_map:

                                System.out.println();
                                System.out.println(player.getCredits().toString());
                                System.out.println();

                                mapFrag mFrag = mapFrag.newInstance(player);
                                trans.replace(R.id.placeholder_planet, mFrag, "map");
                                trans.commit();
                                break;

                        }
                        return true;
                    }
                });
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
