package com.example.spacetrader.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.gamelogic.Player;
import com.example.spacetrader.entity.world.Planet;
import com.example.spacetrader.entity.world.SolarSystem;

public class ViewPlanets extends AppCompatActivity {
    public static final String PLAYER_DATA = "PLAYER_DATA";

    /** an int for the request code */
    private static final int EDIT_REQUEST = 5;

    private PlanetAdapter adapter;

    private SolarSystem solar;

    private Planet[] planets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_planets);

        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        solar = (SolarSystem) getIntent().getSerializableExtra(PLAYER_DATA);

        /*
         Set up our recycler view by grabbing the layout for a single item
         */
        RecyclerView recyclerView = findViewById(R.id.planet_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Setup the adapter for this recycler view
        adapter = new PlanetAdapter();
        recyclerView.setAdapter(adapter);

        setTitle("Planets in Solar System " + solar.getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setPlanetList(solar.getPlanets());

        adapter.setOnPlanetClickListener(new PlanetAdapter.OnPlanetClickListener(){
            @Override
            public void onPlanetClicked(Planet planet) {
                Intent intent = new Intent(ViewPlanets.this, ViewPlanet.class);
                intent.putExtra(PLAYER_DATA, planet);
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }
}
