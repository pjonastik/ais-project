package org.example;

import java.util.Objects;

public class UsbKey {
    private float memoryCapacity;
    private String brand;
    private String model;
    private String createDate;
    private String expirationDate;
    private int exOwners;
    private boolean working;
    private UsbType type;

    enum UsbType {
        A,B,C
    }

    public UsbType getType() {
        return type;
    }

    public UsbKey(int memoryCapacity, String brand, String model, String createDate, String expirationDate, int exOwners, boolean working,UsbType type) {
        this.memoryCapacity = memoryCapacity;
        this.brand = brand;
        this.model = model;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.exOwners = exOwners;
        this.working = working;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsbKey usbKey = (UsbKey) o;
        return memoryCapacity == usbKey.memoryCapacity && exOwners == usbKey.exOwners && working == usbKey.working && Objects.equals(brand, usbKey.brand) && Objects.equals(model, usbKey.model) && Objects.equals(createDate, usbKey.createDate) && Objects.equals(expirationDate, usbKey.expirationDate) && type == usbKey.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memoryCapacity, brand, model, createDate, expirationDate, exOwners, working, type);
    }

    @Override
    public String toString() {
        return "UsbKey{" +
                "memoryCapacity=" + memoryCapacity +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", createDate='" + createDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", exOwners=" + exOwners +
                ", working=" + working +
                ", type=" + type +
                '}';
    }
}
