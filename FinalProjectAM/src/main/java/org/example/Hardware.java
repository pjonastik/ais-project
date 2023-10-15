package org.example;

import java.util.Date;
import java.util.Objects;

public abstract class Hardware {
    String brand;
    String model;
    Date productionDate;
    Date expirationDate;
    boolean isFunctional;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setFunctional(boolean functional) {
        isFunctional = functional;
    }

    @Override
    public String toString() {
        return "Hardware{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", productionDate=" + productionDate +
                ", expirationDate=" + expirationDate +
                ", isFunctional=" + isFunctional +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hardware)) return false;
        Hardware hardware = (Hardware) o;
        return isFunctional == hardware.isFunctional && Objects.equals(brand, hardware.brand) && Objects.equals(model, hardware.model) && Objects.equals(productionDate, hardware.productionDate) && Objects.equals(expirationDate, hardware.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, productionDate, expirationDate, isFunctional);
    }
}
