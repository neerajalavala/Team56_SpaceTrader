package com.example.spacetrader.persistence;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseInteractor implements DatabaseInteractor {
    private StorageReference storageReference;
    private String referenceName;
    long maxDownload;

    public FirebaseInteractor(String referenceName, long maxDownload) {
        this.maxDownload = maxDownload;
        this.referenceName = referenceName;
        storageReference = FirebaseStorage.getInstance().getReference().child(referenceName);
    }

    @Override
    public byte[] download() {
        // TODO download
        return new byte[0];
    }

    @Override
    public void upload(byte[] bytes) {
        storageReference.putBytes(bytes);
    }
}
