package org.example;

import java.util.Objects;

public class Computer extends Hardware implements Workable{
    int numberOfOwners;
    SsdDisk ssdDisk;
    UsbKey usbKey;
    SdCard sdCard;

    public Computer(SsdDisk ssdDisk){
        this.ssdDisk = ssdDisk;
    }       //test OK

    public Computer(SsdDisk ssdDisk, UsbKey usbKey, SdCard sdCard){    // test OK
        this.ssdDisk = ssdDisk;
        this.usbKey = usbKey;
        this.sdCard = sdCard;
    }

    public void mount(SdCard sdCard){       //test OK
        this.sdCard = sdCard;
    }

    public void mount(UsbKey usbKey){
        this.usbKey = usbKey;
    }       //test OK

    public void mount(SsdDisk ssdDisk) {
       throw new SsdDiskUnmoutableException("You cannot add another Ssd Disc");
    }       //test OK

    public UsbKey unmountUsb(){
        UsbKey usb = this.usbKey;
        this.usbKey = null;
        return usb;
    }

    public double getTotalCapacity(){
        double sumOfCapacity = ssdDisk.getTotalCapacity();
        if(usbKey!=null) sumOfCapacity += usbKey.getTotalCapacity();
        if(sdCard!=null) sumOfCapacity += sdCard.getTotalCapacity();
        return sumOfCapacity;
    }

    public double getActualCapacity(){
        double sumOfActualCapacity = ssdDisk.getActualCapacity();
        if(usbKey!=null) sumOfActualCapacity += usbKey.getActualCapacity();
        if(sdCard!=null) sumOfActualCapacity += sdCard.getActualCapacity();
        return sumOfActualCapacity;
    }

    public void useMemory(Memory memory, double memorySize){
        memory.useMemory(memorySize);
    }

    public void removeMemory(Memory memory, double memorySize){
        memory.removeMemory(memorySize);
    }

    public boolean canUseMemory(Memory memory, double memorySize){
        return memory.canUseMemory(memorySize);
    }

    public boolean canRemoveMemory(Memory memory, double memorySize){
        return memory.canRemoveMemory(memorySize);
    }

    public double getPercentageUsage(){
        double memoryCapacity = this.ssdDisk.getTotalCapacity();
        double usedMemory = this.ssdDisk.getActualCapacity();

        if(this.sdCard != null) {
            memoryCapacity += sdCard.getTotalCapacity();
            usedMemory += sdCard.getActualCapacity();
        }

        if (this.usbKey != null){
            memoryCapacity += usbKey.getTotalCapacity();
            usedMemory += usbKey.getActualCapacity();
        }

        return 100 * (usedMemory/memoryCapacity);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "numberOfOwners=" + numberOfOwners +
                ", ssdDisk=" + ssdDisk +
                ", usbKey=" + usbKey +
                ", sdCard=" + sdCard +
                '}';
    }

    public int getNumberOfOwners() {
        return numberOfOwners;
    }

    public SsdDisk getSsdDisk() {
        return ssdDisk;
    }

    public UsbKey getUsbKey() {
        return usbKey;
    }

    public SdCard getSdCard() {
        return sdCard;
    }


    @Override
    public boolean isWorking() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Computer)) return false;
        Computer computer = (Computer) o;
        return numberOfOwners == computer.numberOfOwners && Objects.equals(ssdDisk, computer.ssdDisk) && Objects.equals(usbKey, computer.usbKey) && Objects.equals(sdCard, computer.sdCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfOwners, ssdDisk, usbKey, sdCard);
    }
}
