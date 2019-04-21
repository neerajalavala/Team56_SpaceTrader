package com.example.spacetrader.persistence;

import com.example.spacetrader.entity.Player;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

/**
 * Documents database functions
 */
interface DatabaseInteractor {
    /**
     * Uploads player data to the database
     *
     * @param player player whose data is uploaded
     */
    void upload(Player player);

    /**
     * Creates a player object using database data
     *
     * @return the player object with data from the database
     */
    Task<DocumentSnapshot> download();
}
