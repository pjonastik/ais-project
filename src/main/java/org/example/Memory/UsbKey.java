package org.example.Memory;

import java.util.Objects;
import org.example.ComputerException;
import org.example.Workable;

/*
 *  Vytvor triedu UsbKey s parametrami
 * celková kapacita pamäte
 * značka
 * model
 * dátum výroby
 * dátum expiracie
 * stav reprezentujúci funkčnsť (áno vs nie)
 * usb type (A,,B,C ...)
 */
public class UsbKey extends Memory {

    private int totalMemoryCapacity;
    private String brand;
    private String model;
    private String manufactureDate;
    private String expirationDate;
    private Boolean working;
    private String type;
    public int usedMemory;

    public int getActualMemoryCapacity() {
        return totalMemoryCapacity - usedMemory;
    }

    public UsbKey(int totalMemoryCapacity, String brand, String model, String manufactureDate, String expirationDate, Boolean working, String type) {
        this.totalMemoryCapacity = totalMemoryCapacity;
        this.brand = brand;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.working = working;
        this.type = type;
    }

    public int getTotalMemoryCapacity() {
        return totalMemoryCapacity;
    }

    public Boolean canUseMemory(int memorySize){
        if (getActualMemoryCapacity()-memorySize>0){
            return true;
        }else return false;
    }

    public void useMemory(int memorySize) {
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
        if (!(o instanceof UsbKey)) {
            return false;
        }
        UsbKey usbKey = (UsbKey) o;
        return totalMemoryCapacity == usbKey.totalMemoryCapacity && Objects.equals(brand, usbKey.brand) && Objects.equals(model, usbKey.model)
                && Objects.equals(manufactureDate, usbKey.manufactureDate) && Objects.equals(expirationDate, usbKey.expirationDate) && Objects.equals(working,
                usbKey.working) && Objects.equals(type, usbKey.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalMemoryCapacity, brand, model, manufactureDate, expirationDate, working, type);
    }

    @Override
    public String toString() {
        return "UsbKey{" +
                "totalMemoryCapacity=" + totalMemoryCapacity +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", expirationDate=" + expirationDate +
                ", working=" + working +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public Boolean isWorking(){
        return working;
    }
}
