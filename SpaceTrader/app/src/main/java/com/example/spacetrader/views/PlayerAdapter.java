package com.example.spacetrader.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.Player;

/**
 * Adapts the list of players in the model to be a list of graphical elements in view
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    /** a copy of the list of players in the model */
    private List<Player> playerList = new ArrayList<>();

    /** a listener for a touch event on the player */
    private OnPlayerClickListener listener;

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        // hook up to the view for a single player in the system
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item, parent, false);

        return new PlayerViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {

        //bind the player data for one player
        Player player = playerList.get(position);

        Log.d("APP", "Binding: " + position + " " + playerList.get(position));

        holder.playerName.setText(player.getName());
        holder.difficulty.setText(player.getDiff().toString());

        holder.pilot.setText(player.getPilot().toString());
        holder.fighter.setText(player.getFighter().toString());
        holder.trader.setText(player.getTrader().toString());
        holder.engineer.setText(player.getEngineer().toString());

        holder.credits.setText(player.getCredits().toString());
        holder.shipType.setText(player.getShipType().toString());

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public void setPlayerList(Player player) {
        if (playerList.size() < 1) playerList.add(player);
        notifyDataSetChanged();
    }


    /**
     * This is a holder for the widgets associated with a single entry in the list of players
     */
    class PlayerViewHolder extends RecyclerView.ViewHolder {
        private TextView playerName;
        private TextView difficulty
                ;
        private TextView pilot;
        private TextView fighter;
        private TextView trader;
        private TextView engineer;

        private TextView credits;
        private TextView shipType;



        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.player_name);
            difficulty = itemView.findViewById(R.id.diff_value);

            pilot = itemView.findViewById(R.id.pilot_points);
            fighter= itemView.findViewById(R.id.fighter_points);
            trader = itemView.findViewById(R.id.trader_points);
            engineer = itemView.findViewById(R.id.engineer_points);

            credits = itemView.findViewById(R.id.credit_num);
            shipType = itemView.findViewById(R.id.ship_type);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onPlayerClicked(playerList.get(position));
                    }
                }
            });

        }
    }

    public interface OnPlayerClickListener {
        void onPlayerClicked(Player player);
    }

    public void setOnPlayerClickListener(OnPlayerClickListener listener) {
        this.listener = listener;
    }
}
