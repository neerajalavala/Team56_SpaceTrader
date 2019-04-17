package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

/**
 * Represents player activity
 */
public class ViewPlayerActivity extends AppCompatActivity {

    private static final String PLAYER_DATA = "PLAYER_DATA";

    /** an int for the request code */
    private static final int EDIT_REQUEST = 5;

    private Player player;

    private Player play;

    private TextView credits;

    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_player);

        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        TextView playerName = findViewById(R.id.player_name);
        TextView difficulty = findViewById(R.id.diff_value);

        TextView pilot = findViewById(R.id.pilot_points);
        TextView fighter = findViewById(R.id.fighter_points);
        TextView trader = findViewById(R.id.trader_points);
        TextView engineer = findViewById(R.id.engineer_points);

        credits = findViewById(R.id.credit_num);
        TextView shipType = findViewById(R.id.ship_type);

        GetPlayerViewModel viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);
        player = viewModel.getPlayer();



        playerName.setText(player.getName());
        difficulty.setText(player.getDiff().toString());

        pilot.setText(player.getPilot().toString());
        fighter.setText(player.getFighter().toString());
        trader.setText(player.getTrader().toString());
        engineer.setText(player.getEngineer().toString());

        credits.setText(player.getCredits().toString());
        shipType.setText(player.getShipType().toString());


        setTitle("Select Save");

        Button view_universe = findViewById(R.id.start_button);
        view_universe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPlayerActivity.this, GameStartScreen.class);
                intent.putExtra(PLAYER_DATA, player);
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });


    }
    @Override
    public void onResume() {
        super.onResume();
        credits.setText(player.getCredits().toString());

    }
}
