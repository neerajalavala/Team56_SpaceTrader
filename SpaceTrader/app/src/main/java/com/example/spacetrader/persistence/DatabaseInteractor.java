package com.example.spacetrader.persistence;

import com.example.spacetrader.entity.gamelogic.Player;

public interface DatabaseInteractor {
    /**
     * uploads an object represented as a byte array to the database
     */
    void upload(byte[] bytes);

    /**
     * Downloads a file from the given url to a byte array
     *
     * @return a byte array representing the object
     */
    byte[] download();
}