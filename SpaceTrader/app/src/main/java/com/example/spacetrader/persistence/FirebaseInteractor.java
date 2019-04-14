package com.example.spacetrader.persistence;

import com.example.spacetrader.entity.Player;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseInteractor implements DatabaseInteractor {

    /**
     * Make a new FirebaseInteractor
     *
     * @param referenceName the name of collection where the player is located
     */
    public FirebaseInteractor(String referenceName) {
        StorageReference playerReference = FirebaseStorage.getInstance().getReference().child(referenceName);
    }

    @Override
    public Player download() {
        return null;
    }

    @Override
    public void upload(Player player) {

    }
}
