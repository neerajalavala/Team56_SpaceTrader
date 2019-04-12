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

import android.content.Intent;
import android.app.Activity;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.MarketGood;
import com.example.spacetrader.entity.MarketPlace;
import com.example.spacetrader.entity.CargoHold;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Resources;
import com.example.spacetrader.entity.TechLevel;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

public class TradeItemActivity extends AppCompatActivity {
    /** a key for passing data */
    public static final String PLAYER_DATA = "PLAYER_DATA";
    /** an int for the request code */
    private static final int EDIT_REQUEST = 5;

    private Spinner trade_quantity; //

    private TextView trade_total;

    private GetPlayerViewModel viewModel;

    private Player player;

    private CargoHold hold;

    private TextView MarketGoodName;
    private TextView Price;

    private TextView Quantity;

    private TextView hold_val;
    private TextView creds;

    private Button TradeButton;

    private MarketGood good;

    private MarketPlace mark;


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
        Quantity = findViewById(R.id.quan_num);
        TradeButton = findViewById((R.id.buy_sell_button));
        hold_val = findViewById(R.id.hold_val);
        creds = findViewById(R.id.credits_val);


        /* gets passed good */
        good = (MarketGood) getIntent().getSerializableExtra("TRADE_GOOD");
//
//        player = (Player) getIntent().getSerializableExtra("PLAYER_DATA");

        this.viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);
        this.player = viewModel.getPlayer();
        this.hold = player.getCargoHold();


        /* setting text box values */
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

        this.player = viewModel.getPlayer();
        this.hold = player.getCargoHold();


        if (TradeButton.getText().equals("Sell")) {
            /* selling good from cargo hold */

            if ((hold.getCount() - trade_q) < 0)  {
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
                hold.subQuant(good, trade_q);
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
            } else if((hold.getCount() + trade_q) > hold.getCapacity())  {
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

                mark = player.getCurrentMarketPlace();

                mark.updateGoodQuantity(good, trade_q);

                Intent resultIntent = new Intent();

//                resultIntent.putExtra("UPDATED_MARKET", mark);
//                setResult(Activity.RESULT_OK, resultIntent);

                player.subCredits(trade_v);

                System.out.println(player.getCredits().toString());
                finish();
            }
        }

    }

}
