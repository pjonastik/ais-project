package org.example;

import java.util.Objects;

public class UsbKey extends Device{

    private UsbType type;

    enum UsbType {
        A,B,C
    }

    public UsbKey(String brand, String model, String createDate, String expirationDate, boolean working, UsbType type) {
        super(brand, model, createDate, expirationDate, working);
        this.type = type;
    }
}
