package org.example.Memory;

import java.util.Objects;


public class SdCard extends Memory {

    private final int totalMemoryCapacity;
    private final String brand;
    private final String model;
    private final String manufactureDate;
    private final String expirationDate;
    private final int numberOfPreviousOwner;
    private final Boolean working;
    public int usedMemory;

    public int getActualMemoryCapacity() {
        return totalMemoryCapacity - usedMemory;
    }

    public SdCard(int totalMemoryCapacity, String brand, String model, String manufactureDate, String expirationDate, int numberOfPreviousOwner, Boolean working) {
        this.totalMemoryCapacity = totalMemoryCapacity;
        this.brand = brand;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.numberOfPreviousOwner = numberOfPreviousOwner;
        this.working = working;
    }

    public int getTotalMemoryCapacity() {
        return totalMemoryCapacity;
    }

    public Boolean canUseMemory(int memorySize) {
        return getActualMemoryCapacity() <= memorySize;
    }

    public void useMemory(int memorySize) {
        usedMemory = usedMemory + memorySize;
    }

    public Boolean canRemoveMemory(int memorySize) {
        return usedMemory - memorySize >= 0;
    }

    public void removeMemory(int memorySize) {
        usedMemory = usedMemory - memorySize;
    }

    public float getPerctentalUsage() {
        return 100 / totalMemoryCapacity * usedMemory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SdCard)) {
            return false;
        }
        SdCard sdCard = (SdCard) o;
        return totalMemoryCapacity == sdCard.totalMemoryCapacity && numberOfPreviousOwner == sdCard.numberOfPreviousOwner && Objects.equals(brand, sdCard.brand)
                && Objects.equals(model, sdCard.model) && Objects.equals(manufactureDate, sdCard.manufactureDate) && Objects.equals(expirationDate,
                sdCard.expirationDate) && Objects.equals(working, sdCard.working);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalMemoryCapacity, brand, model, manufactureDate, expirationDate, numberOfPreviousOwner, working);
    }

    @Override
    public String toString() {
        return "SdCard{" +
                "totalMemoryCapacity=" + totalMemoryCapacity +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", expirationDate=" + expirationDate +
                ", numberOfPreviousOwner=" + numberOfPreviousOwner +
                ", working=" + working +
                '}';
    }

    @Override
    public Boolean isWorking() {
        return working;
    }
}
