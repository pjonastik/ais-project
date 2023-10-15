package org.example;

import java.util.Objects;
import org.example.Memory.SdCard;
import org.example.Memory.SsdDisk;
import org.example.Memory.UsbKey;

/*
Validne vytovrený objekt Computer je taký ktorý
 - obsahuje aspon jeden objekt typu SsdDisk.
 - Objekty typu SdCard a UsbKey možu byť “vložené” do objektu typu Computer aj pozdeji

Implmenetuj metodu
 - Computer.mount a Computer.unmountnt- ktora umozni pripojit/odpojit SdCard a UsbKey do pocitataca.
    - Ak by sme sa pokusili vlozit SsdDisk, vyhodi sa vynimka SsdDiskUnmoutableException.
 */
public class Computer implements Workable{
    private String brand;
    private String model;
    private String manufactureDate;
    private String expirationDate;
    private int numberOfPreviousOwner;
    private Boolean working;
    private SsdDisk ssdDisk;

    private SdCard sdCard;
    private UsbKey usbKey;

    public float getPerctentalUsage(){
        return 100F/getTotalMemoryCapacity()*getUsedMemory();
    }

    public int getUsedMemory() {
        int usedMemory = this.ssdDisk.usedMemory;

        if (this.sdCard != null) {
            usedMemory = usedMemory + this.sdCard.usedMemory;
        }
        if (this.usbKey != null){
            usedMemory = usedMemory + this.usbKey.usedMemory;
        }
        return usedMemory;
    }

    public int getTotalMemoryCapacity(){
        int totalMemory = this.ssdDisk.getTotalMemoryCapacity();

        if (this.sdCard != null) {
            totalMemory = totalMemory + this.sdCard.getTotalMemoryCapacity();
        }
        if (this.usbKey != null){
            totalMemory = totalMemory + this.usbKey.getTotalMemoryCapacity();
        }
        return totalMemory;

    }

    public int actualMemory(){
        int actualMemoryCapacity = this.ssdDisk.getActualMemoryCapacity();

        if (this.sdCard != null) {
            actualMemoryCapacity = actualMemoryCapacity + this.sdCard.getActualMemoryCapacity();
        }
        if (this.usbKey != null){
            actualMemoryCapacity = actualMemoryCapacity + this.usbKey.getActualMemoryCapacity();
        }
        return actualMemoryCapacity;
    }

    public void useMemory(int memoryToUse) throws ComputerException {
        if (isWorking()){
            if (actualMemory()>=memoryToUse){
                if(ssdDisk.getActualMemoryCapacity()<=memoryToUse){
                    ssdDisk.useMemory(memoryToUse);
                }else {
                    ssdDisk.useMemory(ssdDisk.getActualMemoryCapacity());
                    memoryToUse=memoryToUse-ssdDisk.getActualMemoryCapacity();
                    if (sdCard.getActualMemoryCapacity()>=memoryToUse){
                        sdCard.useMemory(memoryToUse);
                    }else {
                        sdCard.useMemory(sdCard.getActualMemoryCapacity());
                        memoryToUse=memoryToUse- sdCard.getActualMemoryCapacity();
                        usbKey.useMemory(memoryToUse);
                    }
                }
            }else {
                throw new ComputerException("Not enought of memory");
            }
        }else {
            throw new ComputerException("ComponentIllegalStateException");
        }
    }

    public void removeMemory(int memoryToRemove) throws ComputerException {
        if (isWorking()){
            if (getUsedMemory()<memoryToRemove){
                if(memoryToRemove >= 0){

                }else {
                    throw new ComputerException("You cannot remove less than 0 memory");
                }
            }else {
                throw new ComputerException("You don't use so much memory");
            }
        }else {
            throw new ComputerException("ComponentIllegalStateException");
        }
    }

    public Computer mount(Computer computer, SdCard sdCard){
        return mount(computer, sdCard, null);
    }
    public Computer mount(Computer computer, UsbKey usbKey){
        return mount(computer, null, usbKey);
    }
    public Computer mount(Computer computer, SdCard sdCard, UsbKey usbKey){
        if (usbKey.equals(null)){
            return new Computer(computer.brand, computer.model, computer.manufactureDate, computer.expirationDate, computer.numberOfPreviousOwner, computer.working, computer.ssdDisk, sdCard, computer.usbKey);
        }else {
            if (sdCard.equals(null)){
            return new Computer(computer.brand, computer.model, computer.manufactureDate, computer.expirationDate, computer.numberOfPreviousOwner, computer.working, computer.ssdDisk, computer.sdCard, usbKey);
            }else {
                return new Computer(computer.brand, computer.model, computer.manufactureDate, computer.expirationDate, computer.numberOfPreviousOwner, computer.working, computer.ssdDisk, sdCard, usbKey);
            }
        }
    }

    public Computer unmount(Computer computer, SdCard sdCard, UsbKey usbKey){
        if (usbKey.equals(null)){
            return new Computer(computer.brand, computer.model, computer.manufactureDate, computer.expirationDate, computer.numberOfPreviousOwner, computer.working, computer.ssdDisk, computer.usbKey);
        }else {
            if (sdCard.equals(null)){
                return new Computer(computer.brand, computer.model, computer.manufactureDate, computer.expirationDate, computer.numberOfPreviousOwner, computer.working, computer.ssdDisk, computer.sdCard);
            }else {
                return new Computer(computer.brand, computer.model, computer.manufactureDate, computer.expirationDate, computer.numberOfPreviousOwner, computer.working, computer.ssdDisk);
            }
        }
    }

    public Computer(String brand, String model, String manufactureDate, String expirationDate, int numberOfPreviousOwner, Boolean working, SsdDisk ssdDisk) {
        this(brand, model, manufactureDate, expirationDate, numberOfPreviousOwner, working, ssdDisk, null, null);
    }

    public Computer(String brand, String model, String manufactureDate, String expirationDate, int numberOfPreviousOwner, Boolean working, SsdDisk ssdDisk, SdCard sdCard) {
        this(brand, model, manufactureDate, expirationDate, numberOfPreviousOwner, working, ssdDisk, sdCard, null);
    }

    public Computer(String brand, String model, String manufactureDate, String expirationDate, int numberOfPreviousOwner, Boolean working, SsdDisk ssdDisk, UsbKey usbKey) {
        this(brand, model, manufactureDate, expirationDate, numberOfPreviousOwner, working, ssdDisk, null, usbKey);
    }

    public Computer(String brand, String model, String manufactureDate, String expirationDate, int numberOfPreviousOwner, Boolean working, SsdDisk ssdDisk,
            SdCard sdCard, UsbKey usbKey) {
        this.brand = brand;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.numberOfPreviousOwner = numberOfPreviousOwner;
        this.working = working;
        this.ssdDisk = ssdDisk;
        this.sdCard = sdCard;
        this.usbKey = usbKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Computer)) {
            return false;
        }
        Computer computer = (Computer) o;
        return numberOfPreviousOwner == computer.numberOfPreviousOwner && Objects.equals(brand, computer.brand) && Objects.equals(model, computer.model)
                && Objects.equals(manufactureDate, computer.manufactureDate) && Objects.equals(expirationDate, computer.expirationDate) && Objects.equals(working,
                computer.working);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, manufactureDate, expirationDate, numberOfPreviousOwner, working);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "znacka='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", datumVyroby=" + manufactureDate +
                ", datumExpirace=" + expirationDate +
                ", pocetPredeslychMajitelu=" + numberOfPreviousOwner +
                ", funkcni=" + working +
                '}';
    }


    public void setSdCard(SdCard sdCard) {
        this.sdCard = sdCard;
    }

    public void setUsbKey(UsbKey usbKey) {
        this.usbKey = usbKey;
    }


    public SdCard getSdCard() {
        return sdCard;
    }

    public UsbKey getUsbKey() {
        return usbKey;
    }

    @Override
    public Boolean isWorking(){
//        try {
//            if (working && ssdDisk.isWorking() && sdCard.isWorking() && usbKey.isWorking()) {
//                working = true;
//            }
//        } catch (ComputerException e) {
//            throw new ComputerException("ComponentIllegalStateException");
//        }finally {
//            return working;
//        }
        if (working && ssdDisk.isWorking() && sdCard.isWorking() && usbKey.isWorking()) {
                working = true;
        }else {
            working= false;
        }
        return working;
    }
}
