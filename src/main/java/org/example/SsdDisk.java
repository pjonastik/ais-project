package org.example;

public class SsdDisk extends Device {
    public SsdDisk(int memoryCapacity, String brand, String model, String createDate, String expirationDate, boolean working) {
        super(memoryCapacity, brand, model, createDate, expirationDate, working);
    }

    @Override
    public String toString() {
        return "SsdDisk{}";
    }
}
