package com.example.spacetrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;

import android.view.MenuItem;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

import android.support.design.widget.BottomNavigationView;

import java.util.Objects;

/**
 * Represents game start screen
 */
public class GameStartScreen extends AppCompatActivity {

    private final String new_market = "UPDATED_MARKET";

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_layout);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        planetFrag pFrag = new planetFrag();

        transaction.add(R.id.placeholder_planet, pFrag, "planet");
        transaction.commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        final FragmentTransaction trans =
                                getSupportFragmentManager().beginTransaction();

                        switch (item.getItemId()) {

                            case R.id.action_planet:
                                planetFrag pFrag = new planetFrag();
                                trans.replace(R.id.placeholder_planet, pFrag, "planet");
                                trans.commit();
                                break;

                            case R.id.action_buy:

                                buyFrag bFrag = new buyFrag();
                                trans.replace(R.id.placeholder_planet, bFrag, "buy");
                                trans.commit();
                                break;

                            case R.id.action_sell:

                                sellFrag sFrag = new sellFrag();
                                trans.replace(R.id.placeholder_planet, sFrag, "sell");
                                trans.commit();
                                break;

                            case R.id.action_shipyard:

                                shipFrag shipFrag = new shipFrag();
                                trans.replace(R.id.placeholder_planet, shipFrag, "ship");
                                trans.commit();
                                break;

                            case R.id.action_map:

                                mapFrag mFrag = new mapFrag();
                                trans.replace(R.id.placeholder_planet, mFrag, "map");
                                trans.commit();
                                break;

                        }
                        return true;
                    }
                });
    }

    /**
     * Sets title of action bar
     *
     * @param title message to set
     */
    public void setActionBarTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
