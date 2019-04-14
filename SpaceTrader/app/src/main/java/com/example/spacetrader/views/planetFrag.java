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
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.entity.Universe;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class planetFrag extends Fragment {

    private Planet curr_planet;

    private GetPlayerViewModel viewModel;

    private SolarSystem[] solar_systems;

    private Player player;
    private Universe game;

    private TextView currentPlanet;
    private TextView techLevel;
    private TextView resources;

    private TextView fuel;
    private TextView health;

    private Button refuelButton;
    private Button repairButton;
    private Button saveButton;


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
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);
        this.player = viewModel.getPlayer();
        this.game = viewModel.getPlayerGame();

        this.curr_planet = player.getCurrentPlanet();
        this.solar_systems = game.getSolarSystems();



    }

    Context c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        c = getActivity();
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
        this.saveButton = (Button ) getView().findViewById(R.id.save_button);

//        for (int i = 0; i < solar_systems.length; i++) {
//            if (solar_systems[i].getEntityID() == curr_planet.getSolar_id()) {
//                String currentSystemName = solar_systems[i].getName();
//                ((GameStartScreen) getActivity())
//                        .setActionBarTitle(currentSystemName);
//            }
//        }

        ((GameStartScreen) getActivity()).setActionBarTitle(curr_planet.getName());
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
                player.refuel();
                fuel.setText(player.getFuel() + "/" + player.getShipMaxFuel());
            }
        });

        saveButton.setOnClickListener((view1) -> {
//            FirebaseApp.initializeApp(getContext());
//            Serializer serializer = new Serializer("SpaceTrader.ser", 1024 * 10000);
//            serializer.serialize(player);
            try {
                FileOutputStream fos = c.openFileOutput("SpaceTrader.ser", Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(player);
                os.close();
                fos.close();
            } catch (IOException e) {e.printStackTrace();}
        });
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
//            }
//        }

        super.onResume();

    }
}
