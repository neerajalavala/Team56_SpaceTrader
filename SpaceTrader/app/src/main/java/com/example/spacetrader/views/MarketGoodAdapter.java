package com.example.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.MarketGood;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for market good
 */
public class MarketGoodAdapter extends
        RecyclerView.Adapter<MarketGoodAdapter.MarketGoodViewHolder> {
    /** a copy of the list of MarketGoods in the model */
    private List<MarketGood> MarketGoodList = new ArrayList<>();

    /** a listener for a touch event on the MarketGood */
    private MarketGoodAdapter.OnMarketGoodClickListener listener;

    private final boolean buying = false;


    @NonNull
    @Override
    public MarketGoodAdapter.MarketGoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                     int i) {

        // hook up to the view for a single MarketGood in the system
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trade_item, parent, false);

        return new MarketGoodAdapter.MarketGoodViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MarketGoodAdapter.MarketGoodViewHolder holder,
                                 int position) {

        //bind the MarketGood data for one MarketGood
        MarketGood MarketGood = MarketGoodList.get(position);

//        Log.d("APP", "Binding: " + position + " " + MarketGoodList.get(position));

        holder.MarketGoodName.setText(MarketGood.getMarketGoodType().toString());

        Integer price = MarketGood.getPrice();
        holder.Price.setText(price.toString());

        Integer quantity = MarketGood.getQuantity();
        holder.Quantity_btn.setText(quantity.toString());


    }

    @Override
    public int getItemCount() {
        return MarketGoodList.size();
    }

    /**
     * Sets market good list
     *
     * @param MarketGoods markets goods to set in list
     */
    public void setMarketGoodList(List<MarketGood> MarketGoods) {
        MarketGoodList = MarketGoods;
        notifyDataSetChanged();
    }


    /**
     * This is a holder for the widgets associated with a single entry in the list of MarketGoods
     */
    class MarketGoodViewHolder extends RecyclerView.ViewHolder {
        private final TextView MarketGoodName;
        private final TextView Price;
        private final Button Quantity_btn;

        MarketGoodViewHolder(@NonNull View itemView) {
            super(itemView);
            MarketGoodName = itemView.findViewById(R.id.item_val);
            Price = itemView.findViewById(R.id.price_val);
            Quantity_btn = itemView.findViewById(R.id.quan_button);

            Quantity_btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onMarketGoodClicked(MarketGoodList.get(position));
                    }
                }
            });

        }
    }

    public interface OnMarketGoodClickListener {
        /**
         * click listener for market good
         *
         * @param MarketGood market good that is clicked
         */
        void onMarketGoodClicked(MarketGood MarketGood);
    }

    /**
     *
     * @param listener click listener to set for market good
     */
    public void setOnMarketGoodClickListener(MarketGoodAdapter.OnMarketGoodClickListener listener){
        this.listener = listener;
    }
}
