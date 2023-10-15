package org.example;

import java.util.Objects;

public class SdCard extends Device{
    private int exOwners;

    public SdCard(int memoryCapacity, String brand, String model, String createDate, String expirationDate, boolean working, int exOwners) {
        super(memoryCapacity, brand, model, createDate, expirationDate, working);
        this.exOwners = exOwners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SdCard sdCard = (SdCard) o;
        return exOwners == sdCard.exOwners;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exOwners);
    }

    @Override
    public String toString() {
        return "SdCard{}";
    }
}
