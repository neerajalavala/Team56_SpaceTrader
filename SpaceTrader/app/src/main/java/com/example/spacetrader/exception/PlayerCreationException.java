package com.example.spacetrader.exception;



public class PlayerCreationException extends Exception {
    private String message;

    public PlayerCreationException(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return message;
    }
}
