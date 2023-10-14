package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Computer extends Device{

    private int exOwners;
    private float memoryCapacity;

    public Computer(String brand, String model, String createDate, String expirationDate, boolean working, int exOwners, float memoryCapacity) {
        super(brand, model, createDate, expirationDate, working);
        this.exOwners = exOwners;
        this.memoryCapacity = memoryCapacity;
    }
}
