package com.example.spacetrader.views;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.gamelogic.Player;
import com.example.spacetrader.entity.world.Planet;
import com.example.spacetrader.entity.world.SolarSystem;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

import java.util.List;

public class planetFrag extends Fragment {

    private Planet curr_planet;

    private FragmentActivity listener;

    private GetPlayerViewModel viewModel;

    private SolarSystem[] solar_systems;

    private Player player;

    private TextView currentPlanet;
    private TextView techLevel;
    private TextView resources;

    private TextView fuel;
    private TextView health;

    private Button refuelButton;
    private Button repairButton;


    private String player_data = "PLAYER_DATA";


    public static planetFrag newInstance(Player player) {
        planetFrag frag = new planetFrag();
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

        this.solar_systems = player.getGame().getSolarSystems();

        System.out.println(curr_planet.getName());

        this.viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);
        this.player = viewModel.getPlayer();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.planet_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        this.currentPlanet = (TextView) getView().findViewById(R.id.planet_name);
        this.techLevel = (TextView) getView().findViewById(R.id.t_level_val);
        this.resources= (TextView) getView().findViewById(R.id.res_value);

        this.fuel = (TextView) getView().findViewById(R.id.fuel_val);
        this.health = (TextView) getView().findViewById(R.id.health_val);

        this.refuelButton = (Button) getView().findViewById(R.id.refuel_button);
        this.repairButton = (Button) getView().findViewById(R.id.repair_button);

        for (int i = 0; i < solar_systems.length; i++) {
            if (solar_systems[i].getEntityID() == curr_planet.getSolar_id()) {
                String currentSystemName = solar_systems[i].getName();
                ((GameStartScreen) getActivity())
                        .setActionBarTitle(currentSystemName);
            }
        }

        currentPlanet.setText(curr_planet.getName());
        techLevel.setText(curr_planet.getTechLevel().toString());
        resources.setText(curr_planet.getResources().toString());

        int player_fuel = player.getFuel();
        int ship_health = 100;

        fuel.setText(player_fuel + "/" + player.getShipMaxFuel());
        health.setText(ship_health + "/100");

        refuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refuelShip();
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {

        super.onResume();

    }

    public void refuelShip() {
        while (player.getCredits() - 50 >= 0 && player.getFuel() < player.getShipMaxFuel()) {
            player.addFuel(1);
            player.subCredits(50);
        }
        fuel.setText(player.getFuel() + "/" + player.getShipMaxFuel());
    }
}
