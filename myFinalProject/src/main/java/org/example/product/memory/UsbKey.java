package org.example.product.memory;

public class UsbKey extends Memory {
    public UsbKey(String brand, String model, int totalCapacity) {
        super(brand, model, totalCapacity);
    }

    @Override
    public boolean isMountable() {
        return true;
    }
}
