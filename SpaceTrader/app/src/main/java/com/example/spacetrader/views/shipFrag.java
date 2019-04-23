package com.example.spacetrader.views;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.CargoHold;
import com.example.spacetrader.entity.MarketGood;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.ShipType;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

import java.util.List;
import java.util.Objects;

/**
 * Fragment for ship
 */
public class shipFrag extends Fragment {

    private GetPlayerViewModel viewModel;

    private Player player;

    private ShipType[] ships = ShipType.values();

    private final String player_data = "PLAYER_DATA";

    /** an adapter for the recycler view */
    private ShipAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel .class);
        this.player = viewModel.getPlayer();

        adapter = new ShipAdapter();
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ship_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.ship_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        Integer creds = player.getCredits() + player.getShipType().getPrice();

        ((GameStartScreen) Objects.requireNonNull(getActivity()))
                .setActionBarTitle("Credits: " + creds.toString());


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {

        this.player = viewModel.getPlayer();
        super.onResume();

        Integer creds = player.getCredits() + player.getShipType().getPrice();

        ((GameStartScreen) Objects.requireNonNull(getActivity()))
                .setActionBarTitle("Credits: " + creds.toString());

        adapter.setOnShipClickListener(new ShipAdapter.OnShipClickListener(){
            @Override
            public void onShipClicked(ShipType type) {
                if (type.equals(player.getShipType())) {
                    // same ship
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Already have this ship")
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, id) -> {
                                //do things
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                } else if (type.getPrice() > (player.getShipType().getPrice() + player.getCredits())) {
                    // not enough credits
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Ship too expensive")
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, id) -> {
                                //do things
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                } else if (type.getCapacity() < player.getCargoHold().getCount()) {
                    // too much cargo to transfer
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Too much cargo to transfer")
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, id) -> {
                                //do things
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                } else {
                    player.subCredits(type.getPrice() - player.getShipType().getPrice());
                    player.setShipType(type);
                    player.getCargoHold().setCapacity(type.getCapacity());

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Bought new ship " + type.toString())
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, id) -> {
                                //do things
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }

            }
        });
    }
}