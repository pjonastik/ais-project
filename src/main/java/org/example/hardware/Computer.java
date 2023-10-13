package org.example.hardware;

import org.example.Workable;
import org.example.exceptions.ComponentIllegalStateException;
import org.example.exceptions.SsdDiskUnmoutableException;

import static java.lang.Math.round;

public class Computer extends HardwareParent implements Workable {
    private int pocetPredeslychMajitelu;
    private SSDDisk ssdDisk;
    private SDCard sdCard;
    private USBKey usbKey;

    public Computer(SSDDisk ssdDisk) {
        this.ssdDisk = ssdDisk;
    }


    public void mount(SDCard sdCard){
        this.sdCard = sdCard;
    }

    public void mount(USBKey usbKey){
        this.usbKey = usbKey;
    }

    public void mount(SSDDisk ssdDisk){
        throw new SsdDiskUnmoutableException("Nelze pripojit ssd disk kdyz uz jeden mame");
    }

    public void unmount(SDCard sdCard){
        this.sdCard = null;
    }

    public void unmount(USBKey usbKey){
        this.usbKey = null;
    }

    public int getTotalCapacity() {
        return ssdDisk.getTotalCapacity() +
                usbKey.getTotalCapacity() +
                sdCard.getTotalCapacity();
    }

    public int getActualCapacity() {
        return ssdDisk.getActualCapacity() +
                usbKey.getActualCapacity() +
                sdCard.getActualCapacity();
    }

    public boolean canUseMemory(int memorySize) {
        if (!(ssdDisk.isWorking() && usbKey.isWorking() && sdCard.isWorking())) {
            throw new ComponentIllegalStateException("Jedno z medii neni funkcni, nelze ulozit data do pameti");
        }
        if (memorySize <= getActualCapacity()){
            return true;
        }
        return false;
    }

    public void useMemory(int memorySize) {
        if (canUseMemory(memorySize)){
            if (ssdDisk.getActualCapacity() >= memorySize) {
                ssdDisk.useMemory(memorySize);
            } else if (sdCard.getActualCapacity() >= memorySize){
                sdCard.useMemory(memorySize);
            } else if (usbKey.getActualCapacity() >= memorySize){
                usbKey.useMemory(memorySize);
            }
        }
    }

    public boolean canRemoveMemory(int memorySize) {
        if (!(ssdDisk.isWorking() && usbKey.isWorking() && sdCard.isWorking())) {
            throw new ComponentIllegalStateException("Jedno z medii neni funkcni, nelze odstranit pamet");
        }
        if (memorySize <= (getTotalCapacity() - getActualCapacity())) {
            return true;
        }
        return false;
    }

    public void removeMemory(int memorySize) {
        if (canRemoveMemory(memorySize)) {
            if (ssdDisk.canRemoveMemory(memorySize)) {
                ssdDisk.removeMemory(memorySize);
            } else if (sdCard.canRemoveMemory(memorySize)){
                sdCard.removeMemory(memorySize);
            } else if (usbKey.canRemoveMemory(memorySize)){
                usbKey.removeMemory(memorySize);
            }
        }

    }

    public float getPercentageUsage() {
        return round((float)getActualCapacity() / (float)getTotalCapacity() * 100);
    }

    @Override
    public boolean isWorking() {
        if(this.jeFunkcni) {
            return true;
        }
        return false;
    }
}
