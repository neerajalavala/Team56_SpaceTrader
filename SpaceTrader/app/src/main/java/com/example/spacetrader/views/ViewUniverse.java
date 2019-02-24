package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;



import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.entity.Universe;
import com.example.spacetrader.viewmodels.PlayerListingViewModel;

public class ViewUniverse extends AppCompatActivity {

    public static final String PLAYER_DATA = "PLAYER_DATA";

    /** an int for the request code */
    private static final int EDIT_REQUEST = 5;

    private SolarSystemAdapter adapter;

    private Universe universe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_solar_systems);

        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        universe = (Universe) getIntent().getSerializableExtra(PLAYER_DATA);

        SolarSystem[] solarSystems = universe.getSolarSystems();

        /*
         Set up our recycler view by grabbing the layout for a single item
         */
        RecyclerView recyclerView = findViewById(R.id.solar_system_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Setup the adapter for this recycler view
        adapter = new SolarSystemAdapter();
        recyclerView.setAdapter(adapter);
    }
        @Override
        public void onResume(){
            super.onResume();
            adapter.setSolarSystemList(universe.getSolarSystems());

            adapter.setOnSolarSystemClickListener(new SolarSystemAdapter.OnSolarSystemClickListener() {
                @Override
                public void onSolarSystemClicked(SolarSystem solar) {
                    Intent intent = new Intent(ViewUniverse.this, ViewPlanets.class);
                    intent.putExtra(PLAYER_DATA, solar);
                    startActivityForResult(intent, EDIT_REQUEST);
                }
            });
        }
}
