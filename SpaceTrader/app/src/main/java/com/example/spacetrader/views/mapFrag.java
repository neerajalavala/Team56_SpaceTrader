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
import android.util.Log;
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
import com.example.spacetrader.entity.commerce.MarketPlace;
import com.example.spacetrader.entity.gamelogic.Player;
import com.example.spacetrader.entity.gamelogic.randomEvent;
import com.example.spacetrader.entity.world.Planet;
import com.example.spacetrader.entity.world.SolarSystem;
import com.example.spacetrader.persistence.Serializer;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;
import com.google.firebase.FirebaseApp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class mapFrag extends Fragment {
    private FragmentActivity listener;

    private GetPlayerViewModel viewModel;

    private Player player;

    private Integer speed;

    private Integer turnNum;

    private String player_data = "PLAYER_DATA";

    private SolarSystem[] solar_systems;
    private SolarSystem currentSystem;

    private ArrayList<String> systemNames = new ArrayList<String>();

    private TextView currentSystemName;
    private TextView distance;
    private TextView shipSpeed;
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
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get back arguments
        this.viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);

        this.player = viewModel.getPlayer();

        this.curr_planet = player.getCurrentPlanet();

        this.solar_systems = player.getGame().getSolarSystems();

        this.speed = player.getShipType().getMaxFuel();

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
        this.shipSpeed = (TextView) getView().findViewById(R.id.speed_val);
        this.turns = (TextView) getView().findViewById(R.id.turns_num);

        this.systemsSpinner = (Spinner) getView().findViewById(R.id.systems_spinner);
        this.systemPlanetsSpinner = (Spinner) getView().findViewById(R.id.planet_spinner);

        travelButton= (Button) getView().findViewById(R.id.travel_button);

        int curSysIndex = 0;
        for (int i = 0; i < solar_systems.length; i++) {
            if (solar_systems[i].getEntityID() == curr_planet.getSolar_id()) {
                currentSystem = solar_systems[i];
                curSysIndex = i;
            }
            this.systemNames.add(solar_systems[i].getName());
        }

        final ArrayAdapter<String> systems_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, this.systemNames);
        systems_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        systemsSpinner.setAdapter(systems_adapter);
        systemsSpinner.setSelection(curSysIndex);

        currentSystemName.setText(currentSystem.getName());

        shipSpeed.setText(speed.toString());

        systemsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String next_system_name = (String) systemsSpinner.getSelectedItem();
                SolarSystem next_system = currentSystem;

                for (SolarSystem s : solar_systems) {
                    if (s.getName().contains(next_system_name)) {
                        next_system = s;
                    }
                }

                final SolarSystem next_system_2 = next_system;

                List<Planet> planets = next_system.getPlanets();
                String[] planet_names = new String[3];

                for (int x = 0; x < 3; x++) {
                    planet_names[x] = planets.get(x).getName();
                }

                final ArrayAdapter<String> planets_adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, planet_names);
                planets_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                systemPlanetsSpinner.setAdapter(planets_adapter);
                for (int i = 0; i < planet_names.length; i++) {
                    if (player.getCurrentPlanet().getName().equals(planet_names[i])) systemPlanetsSpinner.setSelection(i);
                }

                if (currentSystem.getEntityID() == next_system_2.getEntityID()) {

                    systemPlanetsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            Planet curr_planet = player.getCurrentPlanet();
                            String next_planet_name = (String) systemPlanetsSpinner.getSelectedItem();

                            Integer turn_num;

                            if (curr_planet.getName().contains(next_planet_name)) {
                                distance.setText("0");
                                turn_num = 0;
                            } else {
                                distance.setText("1");
                                turn_num = 1;
                            }

                            turns.setText(turn_num.toString());

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                        }

                    });

                } else {

                    systemPlanetsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                            int xloc1 = currentSystem.getLocation()[0];
                            int yloc1 = currentSystem.getLocation()[1];

                            int xloc2 = next_system_2.getLocation()[0];
                            int yloc2 = next_system_2.getLocation()[1];

                            Integer dist = (int) Math.sqrt(Math.pow((xloc1 - xloc2), 2) + Math.pow((yloc1 - yloc2), 2));

                            distance.setText(dist.toString());

                            Integer turn_num;

                            if (dist % speed != 0) {
                                turn_num = (dist / speed) + 1;
                            } else {
                                turn_num = (dist / speed);
                            }
                            turns.setText(turn_num.toString());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                        }

                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        travelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Edit", "Changing current planet");
                int playerFuel = player.getFuel();//10;

                System.out.println("traveling");

                Integer turn_num = Integer.parseInt(turns.getText().toString());

                if (turn_num > playerFuel) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Not enough fuel")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                } else {

                    String next_system_name = (String) systemsSpinner.getSelectedItem();
                    SolarSystem next_system = currentSystem;

                    for (SolarSystem s : solar_systems) {
                        if (s.getName().contains(next_system_name)) {
                            next_system = s;
                        }
                    }

                    randomEvent event;
                    for (int i = 0; i < turn_num; i++) {
                        event = player.getGame().getRandomEvent();

                        String event_name = "";
                        if (event == randomEvent.CREDITS) {
                            event_name = "Found Credits!";
                        } else if (event == randomEvent.TRADER) {
                            event_name = "Trader Ship Appeared!";
                        } else if (event == randomEvent.PIRATE) {
                            event_name = "Pirate Attack!";
                        } else if (event == randomEvent.COPS) {
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


                    currentSystemName.setText(next_system_name);
                    turns.setText("0");

                    String next_planet_name = (String) systemPlanetsSpinner.getSelectedItem();
                    List<Planet> planets = next_system.getPlanets();



                    player = viewModel.getPlayer();
                    for (int y = 0; y < 3; y++) {
                        if (planets.get(y).getName().contains(next_planet_name)) {
                            player.setCurrentPlayerPlanet(next_system, y);
                        }
                    }

//                    for (int x = 0; x < players.size(); x++) {
//                        if (players.get(x).getID() == player.getID()) {
//                            //mapFrag.this.player = players.get(x);
                            for (int y = 0; y < 3; y++) {
                                if (planets.get(y).getName().contains(next_planet_name)) {
                                    player.setCurrentPlayerPlanet(next_system, y);
                                }
                            }
//                        }
//                    }

                    player.subFuel(turn_num);

                    System.out.println(player.getCurrentPlanet().getName());
                }
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
        switch (requestCode) {
            case (55): {
                if (resultCode == Activity.RESULT_OK) {
                    MarketPlace returnValue = (MarketPlace) data.getSerializableExtra("UPDATED_MARKET");
                    player.getCurrentPlanet().setMarketPlace(returnValue);
                }
                break;
            }
        }
    }

    public void onTravelPressed(View view) {
        Log.d("Edit", "Changing current planet");
        int playerFuel = 10;

        System.out.println("traveling");

        Integer turn_num = Integer.parseInt(turns.getText().toString());

        if (turn_num > playerFuel) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Not enough fuel")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        } else {

            String next_system_name = (String) systemsSpinner.getSelectedItem();
            SolarSystem next_system = currentSystem;

            for (SolarSystem s : solar_systems) {
                if (s.getName().contains(next_system_name)) {
                    next_system = s;
                }
            }

            String next_planet_name = (String) systemPlanetsSpinner.getSelectedItem();
            List<Planet> planets = next_system.getPlanets();

            System.out.println(player.getCurrentPlanet().getName());



            this.player = viewModel.getPlayer();

            for (int y = 0; y < 3; y++) {
                if (planets.get(y).getName().contains(next_planet_name)) {
                    player.setCurrentPlayerPlanet(next_system, y);
                }
            }

            System.out.println(player.getCurrentPlanet().getName());
        }
    }

}