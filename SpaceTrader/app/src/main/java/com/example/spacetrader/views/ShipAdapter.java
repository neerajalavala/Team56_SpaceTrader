package com.example.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.MarketGood;
import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.ShipType;
import com.example.spacetrader.viewmodels.GetPlayerViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShipAdapter extends
        RecyclerView.Adapter<ShipAdapter.ShipViewHolder>{
    /** a copy of the list of MarketGoods in the model */
    private ShipType[] ships = ShipType.values();

    /** a listener for a touch event on the MarketGood */
    private ShipAdapter.OnShipClickListener listener;

    private GetPlayerViewModel viewModel;

    private Player player;

    @NonNull
    @Override
    public ShipAdapter.ShipViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                     int i) {

        // hook up to the view for a single MarketGood in the system
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ship_item, parent, false);

        return new ShipAdapter.ShipViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ShipAdapter.ShipViewHolder holder,
                                 int position) {
        ShipType type = ships[position];

        try {
            holder.shipPic.setImageResource(type.getPic_loc());
        } catch (Exception e) {

        }

        holder.shipName.setText(type.toString());

        Integer price = type.getPrice();
        holder.Price.setText(price.toString() + " C");

    }

    @Override
    public int getItemCount() {
        return ships.length;
    }

    /**
     * This is a holder for the widgets associated with a single entry in the list of MarketGoods
     */
    class ShipViewHolder extends RecyclerView.ViewHolder {
        private final TextView shipName;
        private final TextView Price;
        private final ImageView shipPic;

        ShipViewHolder(@NonNull View itemView) {
            super(itemView);
            shipName = itemView.findViewById(R.id.ship_name);
            Price = itemView.findViewById(R.id.price_ship);
            shipPic = itemView.findViewById(R.id.shipPic);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onShipClicked(ships[position]);
                    }
                }
            });

        }
    }

    public interface OnShipClickListener {
        /**
         * click listener for market good
         *
         * @param type that is clicked
         */
        void onShipClicked(ShipType type);
    }

    /**
     *
     * @param listener click listener to set for market good
     */
    public void setOnShipClickListener(ShipAdapter.OnShipClickListener listener){
        this.listener = listener;
    }
}
