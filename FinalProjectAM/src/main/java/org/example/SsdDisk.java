package org.example;

import java.util.Objects;

public class SsdDisk extends Memory {

    public SsdDisk(double memoryCapacity) {
        super(memoryCapacity);
    }

    @Override
    public String toString() {
        return "SsdDisk{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", productionDate=" + productionDate +
                ", expirationDate=" + expirationDate +
                ", isFunctional=" + isFunctional +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
