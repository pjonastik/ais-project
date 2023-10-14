package org.example;

import java.util.Objects;

public class SdCard extends Device{
    private int exOwners;

    public SdCard(String brand, String model, String createDate, String expirationDate, boolean working, int exOwners) {
        super(brand, model, createDate, expirationDate, working);
        this.exOwners = exOwners;
    }
}
