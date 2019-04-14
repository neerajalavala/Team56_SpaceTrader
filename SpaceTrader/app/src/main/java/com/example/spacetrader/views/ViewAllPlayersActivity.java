package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

/**
 * This displays all students in the model, regardless of registration
 */
public class ViewAllPlayersActivity extends AppCompatActivity {

    /** a key for passing data */
    public static final String PLAYER_DATA = "PLAYER_DATA";
    /** an int for the request code */
    private static final int EDIT_REQUEST = 5;
    /** our data model */
    private GetPlayerViewModel viewModel;
    /** an adapter for the recycler view */
    private PlayerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_players);

        /*
         Set up our recycler view by grabbing the layout for a single item
         */
        RecyclerView recyclerView = findViewById(R.id.player_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Setup the adapter for this recycler view
        adapter = new PlayerAdapter();
        recyclerView.setAdapter(adapter);

        //grab our view model instance
        viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);

        Log.d("APP", viewModel.getPlayer().toString());

        setTitle("Players");

    }

    @Override
    public void onResume() {
        super.onResume();

        adapter.setPlayerList(viewModel.getPlayer());

        adapter.setOnPlayerClickListener(new PlayerAdapter.OnPlayerClickListener(){
            @Override
            public void onPlayerClicked(Player player) {
                Intent intent = new Intent(ViewAllPlayersActivity.this, ViewPlayerActivity.class);
                intent.putExtra(PLAYER_DATA, player);
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }
}
