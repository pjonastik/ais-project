package org.example;

import java.util.Date;
import java.util.Objects;

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
public class UsbKey implements Workable{

    private int totalMemoryCapacity;
    private String brand;
    private String model;
    private Date manufactureDate;
    private Date expirationDate;
    private Boolean working;
    private String type;

    public UsbKey(int totalMemoryCapacity, String brand, String model, Date manufactureDate, Date expirationDate, Boolean working, String type) {
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
    public Boolean isWorking() {
        return null;
    }
}
