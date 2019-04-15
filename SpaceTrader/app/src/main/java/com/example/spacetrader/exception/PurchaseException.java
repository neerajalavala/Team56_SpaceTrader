package com.example.spacetrader.exception;


/**
 * Represents exception in purchase
 */
public class PurchaseException extends Exception {
    private String message;

    /**
     *
     * @param message message to set as exception
     */
    public PurchaseException(String message) {
        super(message);
        this.message = message;
    }


    @Override
    public String toString() {
        return message;
    }
}
