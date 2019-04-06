package com.example.spacetrader.persistence;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import io.github.jolly.retry.RetryPolicy;
import io.github.jolly.retry.RetryPolicyBuilder;

public class FirebaseInteractor implements DatabaseInteractor {
    private StorageReference storageReference;
    private long maxDownload;

    /**
     * Make a new FirebaseInteractor
     *
     * @param referenceName the name of the offsite file to download
     * @param maxDownload the maximum number of bytes to download
     */
    public FirebaseInteractor(String referenceName, long maxDownload) {
        this.maxDownload = maxDownload;
        storageReference = FirebaseStorage.getInstance().getReference().child(referenceName);
    }

    @Override
    public byte[] download() {
        RetryPolicy<byte[]> retryPolicy = new RetryPolicyBuilder<>()
                                            .attempts(5)
                                            .waitDuration(500)
                                            .build();
        try {
            return retryPolicy.exec(this::downloadHelper);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void upload(byte[] bytes) {
        storageReference.putBytes(bytes);
    }

    private byte[] downloadHelper() {
        BytePassthrough bytePassthrough = new BytePassthrough();
        storageReference.getBytes(maxDownload).addOnSuccessListener(bytePassthrough::setBytes);
        if (bytePassthrough.getBytes() == null) throw new RuntimeException();
        return bytePassthrough.getBytes();
    }
}
