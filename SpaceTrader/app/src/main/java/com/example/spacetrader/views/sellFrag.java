package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.MarketGood;
//import com.example.spacetrader.entity.MarketPlace;
import com.example.spacetrader.entity.CargoHold;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Fragment for selling
 */
public class sellFrag extends Fragment {

//    private MarketPlace market;

    private GetPlayerViewModel viewModel;

    private Player player;

    private CargoHold hold;

    private String trade_good = "TRADE_GOOD";

    private String player_data = "PLAYER_DATA";

    /** an adapter for the recycler view */
    private MarketGoodAdapter adapter;

    private Planet curr_planet;

    /**
     *
     * @param player player whose data is used
     * @return new instance of fragment for selling
     */
    public static sellFrag newInstance(Player player) {
        sellFrag frag = new sellFrag();
        Bundle args = new Bundle();
        args.putSerializable("PLAYER_DATA", player);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        this.viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);
        this.player = viewModel.getPlayer();

        this.curr_planet = player.getCurrentPlanet();

        this.hold = player.getCargoHold();

//        this.market = curr_planet.getMarketPlace();

        adapter = new MarketGoodAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sell_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.trade_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        ((GameStartScreen) Objects.requireNonNull(getActivity()))
                .setActionBarTitle("Hold: " + hold.getCount().toString() +
                        "/" + hold.getCapacity().toString());


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {

        this.player = viewModel.getPlayer();
        this.hold = player.getCargoHold();
//        this.market = player.getCurrentMarketPlace();

        super.onResume();

        ArrayList<MarketGood> goods = hold.getSellableGoods(curr_planet.getTechLevel());

        adapter.setMarketGoodList(goods);

        ((GameStartScreen) Objects.requireNonNull(getActivity()))
                .setActionBarTitle(hold.toString());

        adapter.setOnMarketGoodClickListener(new MarketGoodAdapter.OnMarketGoodClickListener(){
            @Override
            public void onMarketGoodClicked(MarketGood good) {
                Intent intent = new Intent(getActivity(), TradeItemActivity.class);
                intent.putExtra(trade_good, good);
//                intent.putExtra(player_data, player);
                startActivityForResult(intent, 45);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        switch(requestCode) {
//            case (45) : {
//                if (resultCode == Activity.RESULT_OK) {
//                    MarketPlace returnValue = (MarketPlace)
// data.getSerializableExtra("UPDATED_MARKET");
//                    this.market = returnValue;
//                    player.getCurrentPlanet().setMarketPlace(returnValue);
//                }
//                break;
//            }
//        }
    }
}
