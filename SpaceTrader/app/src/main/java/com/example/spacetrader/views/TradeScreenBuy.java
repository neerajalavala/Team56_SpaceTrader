package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.commerce.MarketGood;
import com.example.spacetrader.entity.commerce.MarketPlace;
import com.example.spacetrader.entity.gamelogic.CargoHold;
import com.example.spacetrader.entity.gamelogic.Player;
import com.example.spacetrader.viewmodels.PlayerListingViewModel;

import java.util.List;

public class TradeScreenBuy extends AppCompatActivity {
    /** a key for passing data */
    public static final String PLAYER_DATA = "PLAYER_DATA";
    /** an int for the request code */
    private static final int EDIT_REQUEST = 5;

    private MarketPlace market;

    private PlayerListingViewModel viewModel;

    private Player player;

    private CargoHold hold;

    /** an adapter for the recycler view */
    private MarketGoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        market = (MarketPlace) getIntent().getSerializableExtra(PLAYER_DATA);

        RecyclerView recyclerView = findViewById(R.id.trade_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new MarketGoodAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(PlayerListingViewModel.class);

        List<Player> players = viewModel.getPlayers();

        for (int x = 0; x < players.size(); x++){
            if (players.get(x).getID() == market.getPlayerID()){
                this.player = players.get(x);
                this.hold = player.getCargoHold();
            }
        }

        setTitle("Hold: " + hold.getCount().toString() + "/" + hold.getCapacity().toString()
                        + "  Credits: " + player.getCredits().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setMarketGoodList(market.getBuyableGoods());
        setTitle("Hold: " + hold.getCount().toString() + "/" + hold.getCapacity().toString()
                + "  Credits: " + player.getCredits().toString());

        adapter.setOnMarketGoodClickListener(new MarketGoodAdapter.OnMarketGoodClickListener(){
            @Override
            public void onMarketGoodClicked(MarketGood good) {
                Intent intent = new Intent(TradeScreenBuy.this, TradeItemActivity.class);
                intent.putExtra(PLAYER_DATA, good);
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }
}
