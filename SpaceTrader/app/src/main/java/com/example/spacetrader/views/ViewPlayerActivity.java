package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import com.example.spacetrader.R;
import com.example.spacetrader.viewmodels.PlayerListingViewModel;
import com.example.spacetrader.entity.Player;

public class ViewPlayerActivity extends AppCompatActivity {

    public static final String PLAYER_DATA = "PLAYER_DATA";

    private Player player;

    private PlayerListingViewModel  viewModel;

    private List<Player> players;

    private TextView playerName;
    private TextView difficulty;

    private TextView pilot;
    private TextView fighter;
    private TextView trader;
    private TextView engineer;

    private TextView credits;
    private TextView shipType;


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

        playerName = findViewById(R.id.player_name);
        difficulty = findViewById(R.id.diff_value);

        pilot = findViewById(R.id.pilot_points);
        fighter = findViewById(R.id.fighter_points);
        trader = findViewById(R.id.trader_points);
        engineer = findViewById(R.id.engineer_points);

        credits = findViewById(R.id.credit_num);
        shipType = findViewById(R.id.ship_type);

        player = (Player) getIntent().getSerializableExtra(PLAYER_DATA);

        viewModel = ViewModelProviders.of(this).get(PlayerListingViewModel.class);

        players = viewModel.getPlayers();

        for (Integer i = 0; i < players.size(); i++) {
            if (players.get(i).getID() == player.getID()) {
                playerName.setText(players.get(i).getName());
                difficulty.setText(players.get(i).getDiff().toString());

                pilot.setText(players.get(i).getPilot().toString());
                fighter.setText(players.get(i).getFighter().toString());
                trader.setText(players.get(i).getTrader().toString());
                engineer.setText(players.get(i).getEngineer().toString());

                credits.setText(players.get(i).getCredits().toString());
                shipType.setText(players.get(i).getShipType().toString());
            }
        }
    }
}
