package com.example.spacetrader.persistence;

import com.example.spacetrader.entity.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Serializer {
    private DatabaseInteractor databaseInteractor;
    private byte[] playerBytes;

    public Serializer(String referenceName, long maxDownload) {
        databaseInteractor = new FirebaseInteractor(referenceName, maxDownload);
    }

    public void serialize(Player player) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutput objectOutput;
        try {
            objectOutput = new ObjectOutputStream(byteArrayOutputStream);
            objectOutput.writeObject(player);
            objectOutput.flush();
            playerBytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        databaseInteractor.upload(playerBytes);
    }

    public Player deserialize() {
        playerBytes = databaseInteractor.download();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(playerBytes);
        ObjectInput objectInput;
        Player player;
        try {
            objectInput = new ObjectInputStream(byteArrayInputStream);
            player = (Player) objectInput.readObject();
            objectInput.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return player;
    }
}
