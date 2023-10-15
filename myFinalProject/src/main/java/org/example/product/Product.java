package org.example.product;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public abstract class Product implements Workable {
    protected String brand;
    protected String model;
    protected LocalDate dateOfMade;

    protected LocalDate expirationDate;
    protected boolean isWorking;


    public Product(String brand, String model) {
        this.brand = brand;
        this.model = model;

        this.dateOfMade = LocalDate.now();
        this.expirationDate = dateOfMade.plus(2, ChronoUnit.YEARS);
        this.isWorking = true;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getDateOfMade() {
        return dateOfMade;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean isWorking() {
        return isWorking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return isWorking == product.isWorking &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(model, product.model) &&
                Objects.equals(dateOfMade, product.dateOfMade) &&
                Objects.equals(expirationDate, product.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, dateOfMade, expirationDate, isWorking);
    }

}
