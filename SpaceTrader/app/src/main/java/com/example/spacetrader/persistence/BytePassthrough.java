package com.example.spacetrader.persistence;

/**
 * I hate that this has to exist but the whole
 * final/effectively final thing is a real drag
 */
public class BytePassthrough {
    private byte[] bytes;

    public byte[] getBytes() {
        if (bytes != null) return bytes;
        else return null;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
