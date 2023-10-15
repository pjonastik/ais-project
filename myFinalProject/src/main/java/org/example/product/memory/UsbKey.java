package org.example.product.memory;

import java.util.Objects;

public class UsbKey extends Memory {
    private UsbType type;
    public UsbKey(String brand, String model, int totalCapacity) {
        super(brand, model, totalCapacity);
    }

    enum UsbType {
        A, B, C
    }

    @Override
    public boolean isMountable() {
        return true;
    }

    public UsbType getType() {
        return type;
    }

    public void setType(UsbType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsbKey)) return false;
        if (!super.equals(o)) return false;
        UsbKey usbKey = (UsbKey) o;
        return type == usbKey.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
