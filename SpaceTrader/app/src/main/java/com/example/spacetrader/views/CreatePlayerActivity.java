package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.spacetrader.R;

import com.example.spacetrader.entity.Difficulty;
import com.example.spacetrader.entity.Player;

import com.example.spacetrader.viewmodels.CreatePlayerViewModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class acts as the code behind for creating a new player
 */
public class CreatePlayerActivity extends AppCompatActivity {

    /** reference to our view model */
    private CreatePlayerViewModel viewModel;

    /* ************************
        Widgets we will need for binding and getting information
     */

    private EditText nameField;

    private TextView skill_pts;

    private Spinner pilot_spinner;
    private Spinner fighter_spinner;
    private Spinner trader_spinner;
    private Spinner engineer_spinner;

    private Spinner difficulty_spinner;

    /* ***********************
       Data for player being edited.
     */
    private final Integer[] skill_pt_range = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};

    /* ***********************
       flag for whether this is a new player being added or an existing player being edited;
     */

    public CreatePlayerActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        /*
         * Disables actionbar back button
         */
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        /*
         * Grab the dialog widgets so we can get info for later
         */
        nameField = findViewById(R.id.player_name_input);

        skill_pts = findViewById(R.id.skill_pts_val);

        pilot_spinner = findViewById(R.id.pilot_spinner);
        fighter_spinner = findViewById(R.id.fighter_spinner);
        trader_spinner = findViewById(R.id.trader_spinner);
        engineer_spinner = findViewById(R.id.engineer_spinner);

        difficulty_spinner = findViewById((R.id.diff_spinner));

        Button button = findViewById(R.id.create_button);

        /*
          Set up the adapter to display the allowable skill point ranges in the spinner
         */
        final ArrayAdapter<Integer> skill_pt_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skill_pt_range);
        skill_pt_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pilot_spinner.setAdapter(skill_pt_adapter);
        fighter_spinner.setAdapter(skill_pt_adapter);
        trader_spinner.setAdapter(skill_pt_adapter);
        engineer_spinner.setAdapter(skill_pt_adapter);

        Spinner[] spins = {pilot_spinner, fighter_spinner, trader_spinner, engineer_spinner};

        for (Spinner spin : spins){
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // your code here
                    int pilot = (int) pilot_spinner.getSelectedItem();
                    int fighter = (int) fighter_spinner.getSelectedItem();
                    int trader = (int) trader_spinner.getSelectedItem();
                    int engineer = (int) engineer_spinner.getSelectedItem();

                    Integer pts_left = (Integer) (16 - pilot - fighter - trader - engineer);
                    skill_pts.setText(pts_left.toString());

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
        }

        /* set difficulty spinner */
        difficulty_spinner.setAdapter(new ArrayAdapter<Difficulty>(this, android.R.layout.simple_spinner_item, Difficulty.values()));




        //Setting add player screen
        button.setText("Add");
        setTitle("Adding New Player");

        nameField.setHint("Input Player Name");

        viewModel = ViewModelProviders.of(this).get(CreatePlayerViewModel.class);
    }

    /**
     * Button handler for the add new player button
     *
     * @param view the button that was pressed
     */
    public void onAddPressed(View view) {
//        Log.d("Edit", "Add Player Pressed");

        int pilot = (int) pilot_spinner.getSelectedItem();
        int fighter = (int) fighter_spinner.getSelectedItem();
        int trader = (int) trader_spinner.getSelectedItem();
        int engineer = (int) engineer_spinner.getSelectedItem();

        if (pilot + fighter + trader + engineer != 16) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Exactly 16 skill points must be allocated. ")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (nameField.getText().toString().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Player name cannot be empty. ")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else {

            Difficulty diff = (Difficulty) difficulty_spinner.getSelectedItem();

            Player player = new Player(nameField.getText().toString(), pilot, fighter, trader, engineer, diff);

            Log.d("Edit", "Got new player data: " + player);

            try {
                FileOutputStream fos = this.openFileOutput("SpaceTrader.ser", Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(player);
                os.close();
                fos.close();
            } catch (IOException e) {e.printStackTrace();}
            viewModel.addPlayer(player);

            finish();
        }
    }

    /**
     * Button handler for cancel - just call back pressed
     *
     * @param view the button pressed
     */
    public void onCancelPressed(View view) {
        onBackPressed();
    }
}
