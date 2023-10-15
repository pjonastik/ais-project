package org.example;

import java.util.Objects;

public class SdCard extends Memory {
    int numberOfOwners;

    public SdCard(double memoryCapacity) {
        super(memoryCapacity);
    }

    @Override
    public String toString() {
        return "SdCard{" +
                "numberOfOwners=" + numberOfOwners +
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
        if (!(o instanceof SdCard)) return false;
        if (!super.equals(o)) return false;
        SdCard sdCard = (SdCard) o;
        return numberOfOwners == sdCard.numberOfOwners;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfOwners);
    }
}
