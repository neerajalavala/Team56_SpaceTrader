package com.example.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.SolarSystem;

/**
     * Adapts the list of players in the model to be a list of graphical elements in view
     */
    public class SolarSystemAdapter extends RecyclerView.Adapter<com.example.spacetrader.views.SolarSystemAdapter.SolarSystemViewHolder> {

        /** a copy of the list of players in the model */
        private SolarSystem[] SolarSystemList;

        /** a listener for a touch event on the player */
        private com.example.spacetrader.views.SolarSystemAdapter.OnSolarSystemClickListener listener;

        @NonNull
        @Override
        public com.example.spacetrader.views.SolarSystemAdapter.SolarSystemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

            // hook up to the view for a single player in the system
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.solar_system_item, parent, false);

            return new com.example.spacetrader.views.SolarSystemAdapter.SolarSystemViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(@NonNull com.example.spacetrader.views.SolarSystemAdapter.SolarSystemViewHolder holder, int position) {

            //bind the player data for one player
            SolarSystem solarSystem = SolarSystemList[position];

//            Log.d("APP", "Binding: " + position + " " + SolarSystemList[position].getName());

            if(solarSystem.getName() == null) {
                holder.solarSystemName.setText("None");
            } else {
                holder.solarSystemName.setText(solarSystem.getName());
            }
        }

        @Override
        public int getItemCount() {
            return SolarSystemList.length;
        }

        public void setSolarSystemList(SolarSystem[] solar) {
            SolarSystemList = solar;
            notifyDataSetChanged();
        }


        /**
         * This is a holder for the widgets associated with a single entry in the list of players
         */
        class SolarSystemViewHolder extends RecyclerView.ViewHolder {
            private TextView solarSystemName;

            public SolarSystemViewHolder(@NonNull View itemView) {
                super(itemView);
                solarSystemName = itemView.findViewById(R.id.system_name);

                itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();

                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onSolarSystemClicked(SolarSystemList[position]);
                        }
                    }
                });

            }
        }

        public interface OnSolarSystemClickListener {
            void onSolarSystemClicked(SolarSystem solar);
        }

        public void setOnSolarSystemClickListener(
                com.example.spacetrader.views.SolarSystemAdapter.OnSolarSystemClickListener listener) {
            this.listener = listener;
        }

    }
