package org.example;

public abstract class Device implements IWorkable{

    private String brand;
    private String model;
    private String createDate;
    private String expirationDate;
    private boolean working;

    public Device(String brand, String model, String createDate, String expirationDate, boolean working) {
        this.brand = brand;
        this.model = model;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.working = working;
    }

    @Override
    public boolean isWorking() {
        return working;
    }

}
