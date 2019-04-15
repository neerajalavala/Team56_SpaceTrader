package com.example.spacetrader.views;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
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
import com.example.spacetrader.entity.MarketGood;
import com.example.spacetrader.entity.CargoHold;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Resources;
import com.example.spacetrader.entity.TechLevel;
import com.example.spacetrader.exception.PurchaseException;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

/**
 * Represents trading
 */
public class TradeItemActivity extends AppCompatActivity {
    /** a key for passing data */
    public static final String PLAYER_DATA = "PLAYER_DATA";
    /** an int for the request code */
    private static final int EDIT_REQUEST = 5;

    private Spinner trade_quantity; //

    private TextView trade_total;

    private Player player;

    private MarketGood good;

    private boolean buying;

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
        TextView marketGoodName = findViewById(R.id.item_val);
        TextView price1 = findViewById(R.id.price_val);
        TextView quantity = findViewById(R.id.quan_num);
        Button tradeButton = findViewById((R.id.buy_sell_button));
        TextView hold_val = findViewById(R.id.hold_val);
        TextView credits = findViewById(R.id.credits_val);


        /* gets passed good */
        good = (MarketGood) getIntent().getSerializableExtra("TRADE_GOOD");

        GetPlayerViewModel viewModel = ViewModelProviders.of(this).get(GetPlayerViewModel.class);
        this.player = viewModel.getPlayer();
        CargoHold hold = player.getCargoHold();


        /* setting text box values */
        marketGoodName.setText(good.getType().toString());

        Integer price = good.getPrice();
        price1.setText(price.toString());

        Integer quantity1 = good.getQuantity();
        quantity.setText(quantity1.toString());

        if (good.getTechLevel() == TechLevel.NONE && good.getResources() == Resources.NONE) {
            tradeButton.setText("Sell");
            setTitle("Sell " + good.getType().toString());
            buying = false;
        } else {
            tradeButton.setText("Buy");
            setTitle("Buy " + good.getType().toString());
            buying = true;
        }

        int max_quantity = good.getQuantity();
        Integer[] quantity_values = new Integer[max_quantity];
        for (int i = 0; i < max_quantity; i++) {
            quantity_values[i] = i + 1;
        }

        /*
          Set up the adapter to display the allowable skill point ranges in the spinner
         */
        ArrayAdapter<Integer> quantity_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, quantity_values);
        quantity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        trade_quantity.setAdapter(quantity_adapter);

        credits.setText(player.getCredits().toString());
        hold_val.setText(hold.getCount().toString() + "/" + hold.getCapacity().toString());

        trade_quantity.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {
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

        if (buying) {
            try {
                player.buy(good, trade_q);
                finish();
            } catch (PurchaseException p) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(p.toString())
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, id) -> {
                            //do things
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        } else {
            try {
                player.sell(good, trade_q);
                finish();
            } catch (PurchaseException p) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(p.toString())
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, id) -> {
                            //do things
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }
}
