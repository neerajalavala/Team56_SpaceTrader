package com.example.spacetrader.views;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.CargoHold;
import com.example.spacetrader.entity.MarketGood;
import com.example.spacetrader.entity.MarketPlace;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.TechLevel;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

import java.util.List;
import java.util.Objects;

public class SpaceTrade extends AppCompatActivity {

    private MarketPlace market;

    private Player player;

    private CargoHold hold;

    private final String trade_good = "TRADE_GOOD";

    private final String market_data = "MARKET";

    /** an adapter for the recycler view */
    private MarketGoodAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_fragment);

        GetPlayerViewModel viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);
        this.player = viewModel.getPlayer();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }



        viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);
        this.player = viewModel.getPlayer();
        this.hold = player.getCargoHold();

        try {
            this.market = (MarketPlace) getIntent().getSerializableExtra(market_data);
        } catch (Exception e) {
            this.hold = (CargoHold) getIntent().getSerializableExtra("HOLD");
        }



        adapter = new MarketGoodAdapter();

        RecyclerView recyclerView = Objects.requireNonNull(findViewById(R.id.trade_list));
        recyclerView.setLayoutManager(new LinearLayoutManager(SpaceTrade.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        getWindow().setTitle(hold.toString()
                + "  Credits: " + player.getCredits());
    }
    boolean buying = false;
    @Override
    public void onResume() {

        super.onResume();
        List<MarketGood> goods;

        if (market == null) {
            goods = hold.getSellableGoods(TechLevel.HITECH);
        } else {
            goods = market.getBuyableGoods();
        }

        adapter.setMarketGoodList(goods);

        getWindow().setTitle(hold.toString()
                + "  Credits: " + player.getCredits());

        adapter.setOnMarketGoodClickListener(new MarketGoodAdapter.OnMarketGoodClickListener(){
            @Override
            public void onMarketGoodClicked(MarketGood good) {

                Intent intent = new Intent(SpaceTrade.this, SpaceTradeItem.class);
                intent.putExtra(trade_good, good);
                startActivityForResult(intent, 55);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
