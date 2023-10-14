package org.example;

import java.util.Objects;

public class SsdDisk {
    private float memoryCapacity;
    private String brand;
    private String model;
    private String createDate;
    private String expirationDate;
    private boolean working;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SsdDisk ssdDisk = (SsdDisk) o;
        return memoryCapacity == ssdDisk.memoryCapacity && working == ssdDisk.working && Objects.equals(brand, ssdDisk.brand) && Objects.equals(model, ssdDisk.model) && Objects.equals(createDate, ssdDisk.createDate) && Objects.equals(expirationDate, ssdDisk.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memoryCapacity, brand, model, createDate, expirationDate, working);
    }

    @Override
    public String toString() {
        return "SsdDisk{" +
                "memoryCapacity=" + memoryCapacity +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", createDate='" + createDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", working=" + working +
                '}';
    }
}
