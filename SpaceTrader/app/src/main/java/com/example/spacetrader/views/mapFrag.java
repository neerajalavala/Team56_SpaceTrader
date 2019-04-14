package com.example.spacetrader.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Planet;
import com.example.spacetrader.entity.RandomEvent;
import com.example.spacetrader.entity.SolarSystem;
import com.example.spacetrader.entity.Universe;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

import java.util.ArrayList;
import java.util.List;

public class mapFrag extends Fragment {
    private GetPlayerViewModel viewModel;

    private Player player;
    private Universe game;

    private Integer fuel;

    private Integer turnNum;
    private Integer distanceTo;

    private SolarSystem[] solar_systems;
    private SolarSystem currentSystem;
    private SolarSystem nextSystem;
    private Planet nextPlanet;

    private ArrayList<String> systemNames = new ArrayList<String>();
    private List<SolarSystem> travelableSystems;
    private List<Planet> planetsList;

    private TextView currentSystemName;
    private TextView distance;
    private TextView shipFuel;
    private TextView turns;

    private Spinner systemsSpinner;
    private Spinner systemPlanetsSpinner;

    private Button travelButton;
    private Button saveButton;

    /**
     * an adapter for the recycler view
     */
    private MarketGoodAdapter adapter;

    private Planet curr_planet;

    public static mapFrag newInstance(Player player) {
        mapFrag frag = new mapFrag();
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

        this.game = viewModel.getPlayerGame();

        this.curr_planet = player.getCurrentPlanet();

        this.solar_systems = game.getSolarSystems();

        this.fuel = player.getFuel();

        this.currentSystem = game.getCurrentPlayerSystem();
        this.nextSystem = currentSystem;
        planetsList = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.map_fragment, parent, false);
    }

    //final Context c = getContext();
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        this.currentSystemName = (TextView) getView().findViewById(R.id.system_name);
        this.distance = (TextView) getView().findViewById(R.id.distance_val);
        this.shipFuel = (TextView) getView().findViewById(R.id.speed_val);
        this.turns = (TextView) getView().findViewById(R.id.turns_num);

        this.systemsSpinner = (Spinner) getView().findViewById(R.id.systems_spinner);
        this.systemPlanetsSpinner = (Spinner) getView().findViewById(R.id.planet_spinner);

        travelButton= (Button) getView().findViewById(R.id.travel_button);

        travelableSystems = game.getPlayerTravleablePlanets(player.getFuel());


        final ArrayAdapter<SolarSystem> systems_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, travelableSystems);
        systems_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        systemsSpinner.setAdapter(systems_adapter);
        systemsSpinner.setSelection(systems_adapter.getPosition(game.getCurrentPlayerSystem()));

        final ArrayAdapter<Planet> planets_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, planetsList);
        planets_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        systemPlanetsSpinner.setAdapter(planets_adapter);
        systemPlanetsSpinner.setSelection(planets_adapter.getPosition(curr_planet));

        updateUI();

        systemsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                nextSystem = (SolarSystem) systemsSpinner.getSelectedItem();
                distanceTo = currentSystem.distanceTo(nextSystem);
                turnNum = currentSystem.turnsTo(nextSystem, player.getFuel());

                turns.setText(turnNum.toString());
                distance.setText(distanceTo.toString());
                systemPlanetsSpinner.setSelection(planets_adapter.getPosition(curr_planet));

                planetsList.clear();
                planetsList.addAll(nextSystem.getPlanets());
                planets_adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        systemPlanetsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nextPlanet = (Planet) systemPlanetsSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        travelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.travelTo(nextSystem, nextPlanet, turnNum);


                RandomEvent event;
                for (int i = 0; i < turnNum; i++) {
                    event = game.getRandomEvent();

                    String event_name = "";
                    if (event == RandomEvent.CREDITS) {
                        event_name = "Found Credits!";
                    } else if (event == RandomEvent.TRADER) {
                        event_name = "Trader Ship Appeared!";
                    } else if (event == RandomEvent.PIRATE) {
                        event_name = "Pirate Attack!";
                    } else if (event == RandomEvent.COPS) {
                        event_name = "The Cops!";
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(event_name)
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }

                updateUI();
            }
        });

        ((GameStartScreen) getActivity())
                .setActionBarTitle("Universe");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {


        this.player = viewModel.getPlayer();


//        for (int x = 0; x < players.size(); x++) {
//            if (players.get(x).getID() == player.getID()) {
//                this.player = players.get(x);
//            }
//        }


        super.onResume();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case (55): {
//                if (resultCode == Activity.RESULT_OK) {
//                    MarketPlace returnValue = (MarketPlace) data.getSerializableExtra("UPDATED_MARKET");
//                    player.getCurrentPlanet().setMarketPlace(returnValue);
//                }
//                break;
//            }
//        }
    }

    private void updateUI() {
        currentSystemName.setText(nextSystem.getName());
        fuel = player.getFuel();
        shipFuel.setText(fuel.toString());
        turnNum = 0;
        distanceTo = 0;
        turns.setText(turnNum.toString());
        distance.setText(distanceTo.toString());
    }
}