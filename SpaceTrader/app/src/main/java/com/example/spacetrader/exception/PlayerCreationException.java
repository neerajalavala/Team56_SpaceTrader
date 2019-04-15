package com.example.spacetrader.exception;


/**
 * Represents exception for player creation
 */
public class PlayerCreationException extends Exception {
    private String message;

    /**
     *
     * @param message message to set as exception
     */
    public PlayerCreationException(String message) {
        super(message);
        this.message = message;
    }


    @Override
    public String toString() {
        return message;
    }
}
