package com.example.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.world.Planet;

import java.util.ArrayList;
import java.util.List;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder> {
    /** a copy of the list of Planets in the model */
    private List<Planet> PlanetList = new ArrayList<>();

    /** a listener for a touch event on the Planet */
    private PlanetAdapter.OnPlanetClickListener listener;

    @NonNull
    @Override
    public PlanetAdapter.PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        // hook up to the view for a single Planet in the system
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.planet_item, parent, false);

        return new PlanetAdapter.PlanetViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull PlanetAdapter.PlanetViewHolder holder, int position) {

        //bind the Planet data for one Planet
        Planet Planet = PlanetList.get(position);

        Log.d("APP", "Binding: " + position + " " + PlanetList.get(position));

        holder.PlanetName.setText(Planet.getName());
        holder.Resources.setText(Planet.getResources().toString());
        holder.TechLevel.setText(Planet.getTechLevel().toString());

    }

    @Override
    public int getItemCount() {
        return PlanetList.size();
    }

    public void setPlanetList(List<Planet> Planets) {
        PlanetList = Planets;
        notifyDataSetChanged();
    }


    /**
     * This is a holder for the widgets associated with a single entry in the list of Planets
     */
    class PlanetViewHolder extends RecyclerView.ViewHolder {
        private TextView PlanetName;
        private TextView Resources;
        private TextView TechLevel;




        public PlanetViewHolder(@NonNull View itemView) {
            super(itemView);
            PlanetName = itemView.findViewById(R.id.planet_name);
            Resources = itemView.findViewById(R.id.resources);
            TechLevel = itemView.findViewById(R.id.tech_level);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onPlanetClicked(PlanetList.get(position));
                    }
                }
            });

        }
    }

    public interface OnPlanetClickListener {
        void onPlanetClicked(Planet Planet);
    }

    public void setOnPlanetClickListener(PlanetAdapter.OnPlanetClickListener listener) {
        this.listener = listener;
    }
}

