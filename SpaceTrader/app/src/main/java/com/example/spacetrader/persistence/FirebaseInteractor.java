package com.example.spacetrader.persistence;

import com.example.spacetrader.entity.Player;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Documents Firebase functions
 */
public class FirebaseInteractor implements DatabaseInteractor {

    private DocumentReference playerReference;

    /**
     * Make a new FirebaseInteractor
     *
     * @param referenceName the name of collection where the player is located
     */
    public FirebaseInteractor(String referenceName) {
        playerReference = FirebaseFirestore.getInstance().collection(referenceName).document("player");
    }

    @Override
    public Task<DocumentSnapshot> download() {
        return playerReference.get();
    }

    @Override
    public void upload(Player player) {
        playerReference.set(player);
    }
}
