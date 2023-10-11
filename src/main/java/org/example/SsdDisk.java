package org.example;

import java.util.Date;
import java.util.Objects;

/*
 *  Vytvor triedu SsdDisk s parametrami
 * celková kapacita pamäte
 * značka
 * model
 * dátum výroby
 * dátum expiracie
 * stav reprezentujúci funkčnsť (áno vs nie)
 */
public class SsdDisk implements Workable{

    private int totalMemoryCapacity;
    private String brand;
    private String model;
    private Date manufactureDate;
    private Date expirationDate;
    private Boolean working;

    public SsdDisk(int totalMemoryCapacity, String brand, String model, Date manufactureDate, Date expirationDate, Boolean working) {
        this.totalMemoryCapacity = totalMemoryCapacity;
        this.brand = brand;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
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
        if (!(o instanceof SsdDisk)) {
            return false;
        }
        SsdDisk ssdDisk = (SsdDisk) o;
        return totalMemoryCapacity == ssdDisk.totalMemoryCapacity && Objects.equals(brand, ssdDisk.brand) && Objects.equals(model, ssdDisk.model)
                && Objects.equals(manufactureDate, ssdDisk.manufactureDate) && Objects.equals(expirationDate, ssdDisk.expirationDate) && Objects.equals(working,
                ssdDisk.working);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalMemoryCapacity, brand, model, manufactureDate, expirationDate, working);
    }

    @Override
    public String toString() {
        return "SsdDisk{" +
                "totalMemoryCapacity=" + totalMemoryCapacity +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", expirationDate=" + expirationDate +
                ", working=" + working +
                '}';
    }

    @Override
    public Boolean isWorking() {
        return null;
    }
}
