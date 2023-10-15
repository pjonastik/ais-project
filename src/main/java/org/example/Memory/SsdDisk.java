package org.example.Memory;

import java.util.Objects;
import org.example.ComputerException;
import org.example.Workable;

/*
 *  Vytvor triedu SsdDisk s parametrami
 * celková kapacita pamäte
 * značka
 * model
 * dátum výroby
 * dátum expiracie
 * stav reprezentujúci funkčnsť (áno vs nie)
 */
public class SsdDisk extends Memory {

    private int totalMemoryCapacity;
    private String brand;
    private String model;
    private String manufactureDate;
    private String expirationDate;
    private Boolean working;
    public int usedMemory;

    public int getActualMemoryCapacity() {
        return totalMemoryCapacity - usedMemory;
    }

    public SsdDisk(int totalMemoryCapacity, String brand, String model, String manufactureDate, String expirationDate, Boolean working) {
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

    public Boolean canUseMemory(int memorySize){
        if (getActualMemoryCapacity()-memorySize>0){
            return true;
        }else return false;
    }

    public void useMemory(int memorySize){
        usedMemory=usedMemory+memorySize;
    }

    public Boolean canRemoveMemory(int memorySize) {
        if (usedMemory-memorySize>=0){
            return true;
        }else return false;
    }
    public void removeMemory(int memorySize){
        usedMemory = usedMemory-memorySize;
    }
    public float getPerctentalUsage(){
        return 100/totalMemoryCapacity*usedMemory;
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
    public Boolean isWorking(){
        return working;
    }
}
