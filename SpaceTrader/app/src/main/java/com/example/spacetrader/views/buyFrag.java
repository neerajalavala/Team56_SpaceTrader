package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.content.Context;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.MarketGood;
import com.example.spacetrader.entity.MarketPlace;
import com.example.spacetrader.entity.CargoHold;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Fragment for buying
 */
public class buyFrag extends Fragment {

    private MarketPlace market;

    private Player player;

    private CargoHold hold;

    private final String trade_good = "TRADE_GOOD";

    private final String player_data = "PLAYER_DATA";

    /** an adapter for the recycler view */
    private MarketGoodAdapter adapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GetPlayerViewModel viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);
        this.player = viewModel.getPlayer();

        Planet curr_planet = player.getCurrentPlanet();

        this.hold = player.getCargoHold();

        this.market = player.getCurrentMarketPlace();

        viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);
        this.player = viewModel.getPlayer();


        adapter = new MarketGoodAdapter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.buy_fragment, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.trade_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        ((GameStartScreen) Objects.requireNonNull(getActivity()))
                .setActionBarTitle(hold.toString()
                        + "  Credits: " + player.getCredits());


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {

//        this.player = viewModel.getPlayer();
//        this.hold = player.getCargoHold();
//        this.market = player.getCurrentMarketPlace();


//        for (int x = 0; x < players.size(); x++){
//            if (players.get(x).getID() == player.getID()){
//                this.player = players.get(x);
//                this.hold = player.getCargoHold();
//                this.market = player.getCurrentPlanet().getMarketPlace();
//            }
//        }


        super.onResume();

        List<MarketGood> goods = market.getBuyableGoods();

        adapter.setMarketGoodList(goods);

        ((GameStartScreen) Objects.requireNonNull(getActivity()))
                .setActionBarTitle(hold.toString()
                + "  Credits: " + player.getCredits());

        adapter.setOnMarketGoodClickListener(new MarketGoodAdapter.OnMarketGoodClickListener(){
            @Override
            public void onMarketGoodClicked(MarketGood good) {
                Intent intent = new Intent(getActivity(), TradeItemActivity.class);
                intent.putExtra(trade_good, good);
                intent.putExtra(player_data, player);
                startActivityForResult(intent, 55);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        switch(requestCode) {
//            case (55) : {
//                if (resultCode == Activity.RESULT_OK) {
//                    MarketPlace returnValue = (MarketPlace) data.getSerializable
//                     Extra("UPDATED_MARKET");
//                    this.market = returnValue;
//                    player.getCurrentPlanet().setMarketPlace(returnValue);
//                }
//                break;
//            }
//        }
    }
}