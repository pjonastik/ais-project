package org.example;


import Exceptions.ComponentIllegalStateException;
import Exceptions.SsdDiskUnmoutableException;

import java.util.Objects;

public class Computer implements IWorkable{

    private String brand;
    private String model;
    private String createDate;
    private String expirationDate;
    private boolean working;
    private int exOwners;
    private float memoryCapacity;
    private SsdDisk mainDrive;
    private SdCard sdCard;
    private UsbKey usbKey;

    public Computer(String brand, String model, String createDate, String expirationDate, boolean working, int exOwners,
                    SsdDisk mainDrive) {
        this.brand = brand;
        this.model = model;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.exOwners = exOwners;
        this.working = working;
        this.mainDrive = mainDrive;
        this.sdCard = sdCard;
        this.usbKey = usbKey;
    }

    public void mount(SdCard input){
        this.sdCard=input;
    }

    public void mount(UsbKey input){
        this.usbKey=input;
    }

    public void mount(SsdDisk input) throws SsdDiskUnmoutableException {
        throw new SsdDiskUnmoutableException("Ssd disk není možné připojit!");
    }

    @Override
    public boolean isWorking() {
        return working;
    }

    public void unmount(SdCard input){
        this.sdCard=null;
    }

    public void unmount(UsbKey input){
        this.usbKey=null;
    }
    public int getTotalCapacity() {
        int totalCapacity=0;
        totalCapacity += mainDrive.getTotalCapacity();
        if (sdCard != null) {
            totalCapacity += sdCard.getTotalCapacity();
        }
        if (usbKey != null) {
            totalCapacity += usbKey.getTotalCapacity();
        }
        return totalCapacity;
    }

    public int getActualCapacity() {
        int actualCapacity=0;
        actualCapacity += mainDrive.getActualCapacity();
        if (sdCard != null) {
            actualCapacity += sdCard.getActualCapacity();
        }
        if (usbKey != null) {
            actualCapacity += usbKey.getActualCapacity();
        }
        return actualCapacity;
    }

    public boolean canUseMemory(int memorySize){
        int usedMemory=0;
        usedMemory += mainDrive.getUsedMemory();
        if (sdCard != null) {
            usedMemory += sdCard.getUsedMemory();
        }
        if (usbKey != null) {
            usedMemory += usbKey.getUsedMemory();
        }
        if (usedMemory+memorySize>getTotalCapacity()){
            return false;}
        else {
            return true;}
    }

    public enum DeviceType{
        SSD, USB, SD
    }
    public void useMemory(int memorySize,DeviceType type) throws ComponentIllegalStateException{
        switch (type){
            case SSD:
                if(mainDrive.isWorking() == false){
                    throw new ComponentIllegalStateException("SSD disk nefunguje!");
                }
                mainDrive.useMemory(memorySize);
                break;
            case SD:
                if(sdCard.isWorking() == false){
                    throw new ComponentIllegalStateException("SD karta nefunguje!");
                }
                sdCard.useMemory(memorySize);
                break;
            case USB:
                if(usbKey.isWorking() == false){
                    throw new ComponentIllegalStateException("USB klíč nefunguje!");
                }
                usbKey.useMemory(memorySize);
                break;
        };
    }

    public void removeMemory(int memorySize,DeviceType type) throws ComponentIllegalStateException{
        switch (type){
            case SSD:
                if(mainDrive.isWorking() == false){
                    throw new ComponentIllegalStateException("SSD disk nefunguje!");
                }
                mainDrive.removeMemory(memorySize);
                break;
            case SD:
                if(sdCard.isWorking() == false){
                    throw new ComponentIllegalStateException("SD karta nefunguje!");
                }
                sdCard.removeMemory(memorySize);
                break;
            case USB:
                if(usbKey.isWorking() == false){
                    throw new ComponentIllegalStateException("USB klíč nefunguje!");
                }
                usbKey.removeMemory(memorySize);
                break;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return working == computer.working && exOwners == computer.exOwners && Float.compare(memoryCapacity, computer.memoryCapacity) == 0 && Objects.equals(brand, computer.brand) && Objects.equals(model, computer.model) && Objects.equals(createDate, computer.createDate) && Objects.equals(expirationDate, computer.expirationDate) && Objects.equals(mainDrive, computer.mainDrive) && Objects.equals(sdCard, computer.sdCard) && Objects.equals(usbKey, computer.usbKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, createDate, expirationDate, working, exOwners, memoryCapacity, mainDrive, sdCard, usbKey);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", createDate='" + createDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", working=" + working +
                ", exOwners=" + exOwners +
                ", memoryCapacity=" + memoryCapacity +
                ", mainDrive=" + mainDrive +
                ", sdCard=" + sdCard +
                ", usbKey=" + usbKey +
                '}';
    }
}
