package com.example.spacetrader.persistence;

import com.example.spacetrader.entity.Player;

public interface DatabaseInteractor {
    /**
     * Uploads player data to the database
     */
    void upload(Player player);

    /**
     * Creates a player object using database data
     *
     * @return the player object with data from the database
     */
    Player download();
}
