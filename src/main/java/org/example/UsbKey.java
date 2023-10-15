package org.example;

import java.util.Objects;

public class UsbKey extends Device{

    private UsbType type;

    enum UsbType {
        A,B,C
    }

    public UsbKey(int memoryCapacity, String brand, String model, String createDate, String expirationDate, boolean working, UsbType type) {
        super(memoryCapacity, brand, model, createDate, expirationDate, working);
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsbKey usbKey = (UsbKey) o;
        return type == usbKey.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "UsbKey{" +
                "type=" + type +
                '}';
    }
}
