package com.example.spacetrader.views;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.commerce.MarketGood;
import com.example.spacetrader.entity.gamelogic.CargoHold;
import com.example.spacetrader.entity.gamelogic.Player;
import com.example.spacetrader.entity.world.Resources;
import com.example.spacetrader.entity.world.TechLevel;
import com.example.spacetrader.viewmodels.PlayerListingViewModel;

import java.util.List;

public class TradeItemActivity extends AppCompatActivity {
    /** a key for passing data */
    public static final String PLAYER_DATA = "PLAYER_DATA";
    /** an int for the request code */
    private static final int EDIT_REQUEST = 5;

    private Spinner trade_quantity;

    private TextView trade_total;

    private PlayerListingViewModel viewModel;

    private Player player;

    private CargoHold hold;

    private TextView MarketGoodName;
    private TextView Price;
    private TextView Quantity;

    private TextView hold_val;
    private TextView creds;

    private Button TradeButton;



    private MarketGood good;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell_item);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }

        /* setting views to ids */
        trade_quantity = findViewById(R.id.trade_quantity);
        trade_total = findViewById(R.id.total_val);
        MarketGoodName = findViewById(R.id.item_val);
        Price = findViewById(R.id.price_val);
        Quantity = findViewById(R.id.quantity_val);
        TradeButton = findViewById((R.id.buy_sell_button));
        hold_val = findViewById(R.id.hold_val);
        creds = findViewById(R.id.credits_val);


        /* gets passed good */
        good = (MarketGood) getIntent().getSerializableExtra(PLAYER_DATA);

        /* setting texy box values */
        MarketGoodName.setText(good.getType().toString());

        Integer price = (Integer)good.getPrice();
        Price.setText(price.toString());

        Integer quan = (Integer)good.getQuantity();
        Quantity.setText(quan.toString());

        if (good.getTechLevel() == TechLevel.NONE && good.getResources() == Resources.NONE) {
            TradeButton.setText("Sell");
            setTitle("Sell " + good.getType().toString());
        } else {
            TradeButton.setText("Buy");
            setTitle("Buy " + good.getType().toString());
        }

        int max_quan = good.getQuantity();
        Integer[] quan_vals = new Integer[max_quan];
        for (int i = 0; i < max_quan; i++) {
            quan_vals[i] = i + 1;
        }

        /*
          Set up the adapter to display the allowable skill point ranges in the spinner
         */
        ArrayAdapter<Integer> quantity_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quan_vals);
        quantity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        trade_quantity.setAdapter(quantity_adapter);

        viewModel = ViewModelProviders.of(this).get(PlayerListingViewModel.class);

        List<Player> players = viewModel.getPlayers();

        for (int x = 0; x < players.size(); x++){
            if (players.get(x).getID() == good.getPlayerID()){
                this.player = players.get(x);
                this.hold = player.getCargoHold();
            }
        }

        creds.setText(player.getCredits().toString());
        hold_val.setText(hold.getCount().toString() + "/" + hold.getCapacity().toString());

        trade_quantity.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Integer trade_q = (Integer) trade_quantity.getSelectedItem();
                Integer trade_v = trade_q * good.getPrice();
                trade_total.setText(trade_v.toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    /**
     * Button handler for the add new player button
     *
     * @param view the button that was pressed
     */
    public void onTradePressed(View view) {
        Integer trade_q = (Integer) trade_quantity.getSelectedItem();
        Integer trade_v = trade_q * good.getPrice();

        if (TradeButton.getText().equals("Sell")) {
            /* selling good from cargo hold */

            if (!hold.subQuant(good, trade_q)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Cannot Sell more than you have")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                player.addCredits(trade_v);
                finish();
            }

        } else {
            /* buying good from marketplace */

            if (trade_v > player.getCredits()) {
                /* trade too expensive */

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Not enough credits to buy")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            } else if(!hold.addGood(good, trade_q)) {
                /* not enough space in hold */

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Not enough space in hold")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            } else {

                hold.addGood(good, trade_q);
                good.subQuantity(trade_q);

                player.subCredits(trade_v);
                finish();
            }
        }

    }

}
