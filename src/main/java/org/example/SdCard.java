package org.example;

import java.util.Objects;

public class SdCard {
    private float memoryCapacity; //byte?
    private String brand;
    private String model;
    private String createDate;
    private String expirationDate;
    private int exOwners;
    private boolean working;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SdCard sdCard = (SdCard) o;
        return memoryCapacity == sdCard.memoryCapacity && exOwners == sdCard.exOwners && working == sdCard.working && Objects.equals(brand, sdCard.brand) && Objects.equals(model, sdCard.model) && Objects.equals(createDate, sdCard.createDate) && Objects.equals(expirationDate, sdCard.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memoryCapacity, brand, model, createDate, expirationDate, exOwners, working);
    }

    @Override
    public String toString() {
        return "SdCard{" +
                "memoryCapacity=" + memoryCapacity +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", createDate='" + createDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", exOwners=" + exOwners +
                ", working=" + working +
                '}';
    }
}
