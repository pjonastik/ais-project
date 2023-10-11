package org.example;

import java.util.Date;
import java.util.Objects;

/*

 */
public class SdCard implements Workable{

    private int totalMemoryCapacity;
    private String brand;
    private String model;
    private Date manufactureDate;
    private Date expirationDate;
    private int numberOfPreviousOwner;
    private Boolean working;

    public SdCard(int totalMemoryCapacity, String brand, String model, Date manufactureDate, Date expirationDate, int numberOfPreviousOwner, Boolean working) {
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

    public int getActualCapacity(int consumedMemory){
        return totalMemoryCapacity - consumedMemory;
    }

    public Boolean canUseMemory(int memorySize){
        if (getActualCapacity(memorySize)>0){
            return true;
        }else return false;
    }

    public void useMemory(int memorySize){

    }
    public void canRemoveMemory(int memorySize) {

    }
    public void removeMemory(int memorySize){

    }
    public float getPerctentalUsage(){
        return 0;
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
        return null;
    }
}
