package org.example.product.memory;

public class SsdDisk extends Memory {
    public SsdDisk(String brand, String model, int totalCapacity) {
        super(brand, model, totalCapacity);
    }


    @Override
    public boolean isMountable() {
        return false;
    }
}
