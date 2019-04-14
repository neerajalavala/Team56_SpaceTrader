package com.example.spacetrader.exception;



public class PurchaseException extends Exception {
    private String message;

    public PurchaseException(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return message;
    }
}
