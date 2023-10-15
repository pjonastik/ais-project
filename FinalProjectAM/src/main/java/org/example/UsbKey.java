package org.example;

import java.util.Objects;

public class UsbKey extends Memory {

    char usbType;

    public UsbKey(double memoryCapacity) {
        super(memoryCapacity);
    }

    @Override
    public String toString() {
        return "UsbKey{" +
                "usbType=" + usbType +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", productionDate=" + productionDate +
                ", expirationDate=" + expirationDate +
                ", isFunctional=" + isFunctional +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsbKey)) return false;
        if (!super.equals(o)) return false;
        UsbKey usbKey = (UsbKey) o;
        return usbType == usbKey.usbType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), usbType);
    }
}
