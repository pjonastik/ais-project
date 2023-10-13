package org.example.product.memory;

import org.example.product.Ownerable;

import java.util.Objects;

public class SdCard extends Memory implements Ownerable {

    private int numberOfPreviousOwners;

    public SdCard(String brand, String model, int totalCapacity) {
        super(brand, model, totalCapacity);
    }

    public void setNumberOfPreviousOwners(int numberOfPreviousOwners) {
        this.numberOfPreviousOwners = numberOfPreviousOwners;
    }

    @Override
    public int numberOfPreviousOwners() {
        return numberOfPreviousOwners;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SdCard sdCard = (SdCard) o;
        return numberOfPreviousOwners == sdCard.numberOfPreviousOwners;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfPreviousOwners);
    }

    @Override
    public String toString() {
        return "SdCard{" +
                "numberOfPreviousOwners=" + numberOfPreviousOwners +
                ", totalCapacity=" + totalCapacity +
                ", actualCapacity=" + actualCapacity +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", dateOfMade=" + dateOfMade +
                ", expirationDate=" + expirationDate +
                ", isWorking=" + isWorking +
                '}';
    }

    @Override
    public boolean isMountable() {
        return true;
    }
}
