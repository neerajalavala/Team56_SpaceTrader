package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.content.Context;
import android.app.Activity;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.commerce.MarketGood;
import com.example.spacetrader.entity.commerce.MarketPlace;
import com.example.spacetrader.entity.gamelogic.CargoHold;
import com.example.spacetrader.entity.gamelogic.Player;
import com.example.spacetrader.entity.world.Planet;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

import java.util.ArrayList;
import java.util.List;

public class buyFrag extends Fragment {
    private FragmentActivity listener;

    private MarketPlace market;

    private GetPlayerViewModel viewModel;

    private Player player;

    private CargoHold hold;

//    private Player players;

    private String trade_good = "TRADE_GOOD";

    private String player_data = "PLAYER_DATA";

    /** an adapter for the recycler view */
    private MarketGoodAdapter adapter;

    private Planet curr_planet;

    public static buyFrag newInstance(Player player) {
        buyFrag frag = new buyFrag();
        Bundle args = new Bundle();
        args.putSerializable("PLAYER_DATA", player);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        this.player = (Player) getArguments().getSerializable("PLAYER_DATA");

        this.curr_planet = player.getCurrentPlanet();

        System.out.println(curr_planet.getName());

        this.hold = player.getCargoHold();

        this.market = curr_planet.getMarketPlace();

        this.viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);
        this.player = viewModel.getPlayer();

        adapter = new MarketGoodAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.buy_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.trade_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        ((GameStartScreen) getActivity())
                .setActionBarTitle("Hold: " + hold.getCount().toString() + "/" + hold.getCapacity().toString()
                        + "  Credits: " + player.getCredits().toString());


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {

//        for (int x = 0; x < players.size(); x++){
//            if (players.get(x).getID() == player.getID()){
//                this.player = players.get(x);
//                this.hold = player.getCargoHold();
//                this.market = player.getCurrentPlanet().getMarketPlace();
//            }
//        }

        super.onResume();

        ArrayList<MarketGood> goods = market.getBuyableGoods();

        adapter.setMarketGoodList(goods);

        ((GameStartScreen) getActivity())
                .setActionBarTitle("Hold: " + hold.getCount().toString() + "/" + hold.getCapacity().toString()
                + "  Credits: " + player.getCredits().toString());

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
        switch(requestCode) {
            case (55) : {
                if (resultCode == Activity.RESULT_OK) {
                    MarketPlace returnValue = (MarketPlace) data.getSerializableExtra("UPDATED_MARKET");
                    this.market = returnValue;
                    player.getCurrentPlanet().setMarketPlace(returnValue);
                }
                break;
            }
        }
    }
}