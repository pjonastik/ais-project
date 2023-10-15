package org.example;

public abstract class Device implements IWorkable {

    private int memoryCapacity;
    private String brand;
    private String model;
    private String createDate;
    private String expirationDate;
    private boolean working;
    private int usedMemory = 0;

    public Device(int memoryCapacity, String brand, String model, String createDate, String expirationDate, boolean working) {
        this.memoryCapacity = memoryCapacity;
        this.brand = brand;
        this.model = model;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.working = working;
    }

    @Override
    public boolean isWorking() {
        return working;
    }

    public int getTotalCapacity() {
        return memoryCapacity;
    }

    public int getActualCapacity() {
        return memoryCapacity-usedMemory;
    }

    public boolean canUseMemory(int memorySize){
        if (usedMemory+memorySize>memoryCapacity){
            return false;}
        else {
            return true;}
    }

    public void useMemory(int memorySize){
        usedMemory+=memorySize;
    }

    public boolean canRemoveMemory(int memorySize){
        if (usedMemory>memorySize){
            return true;}
        else {
            return false;}
    }
    public void removeMemory(int memorySize){
        usedMemory-=memorySize;
    }

    public float getPercentageUsage(){
        return (float) (((float) usedMemory/(float) memoryCapacity) * 100.0);
    }

    public int getUsedMemory() {
        return usedMemory;
    }
}
