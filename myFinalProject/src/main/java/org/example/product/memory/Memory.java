package org.example.product.memory;

import org.example.product.Product;
import org.example.product.memory.exception.ComponentIllegalStateException;
import org.example.product.memory.exception.MemoryInvalidException;
import org.example.product.memory.exception.RemoveMemoryException;
import org.example.product.memory.exception.UseMemoryException;

import java.util.Objects;

public abstract class Memory extends Product implements Memorable {
    protected int totalCapacity; //MB
    protected int actualCapacity; //MB
    public Memory(String brand, String model, int totalCapacity) {
        super(brand, model);
        if(totalCapacity <= 0) {
            throw new MemoryInvalidException("Total capacity cannot be less or equals than 0.");
        }
        this.totalCapacity = totalCapacity;
    }

    @Override
    public int getTotalCapacity() {
        return totalCapacity;
    }

    @Override
    public int getActualCapacity() {
        return actualCapacity;
    }

    @Override
    public int getFreeCapacity() {
        return totalCapacity - actualCapacity;
    }

    @Override
    public boolean canUseMemory(int memorySize) {
        int freeCapacity = totalCapacity - actualCapacity;
        return (freeCapacity - memorySize) >= 0;
    }

    @Override
    public void useMemory(int memorySize) {
        if (!isWorking()) {
            throw new ComponentIllegalStateException("Memory is not working :(");
        }
        if (!canUseMemory(memorySize)) {
            throw new UseMemoryException(
                    String.format("Actual usage is '%s'. Cannot use '%s'. ",
                            actualCapacity, memorySize));
        }
        actualCapacity += memorySize;
    }

    @Override
    public boolean canRemoveMemory(int memorySize) {
        return (actualCapacity - memorySize) >= 0;
    }

    @Override
    public void removeMemory(int memorySize) {
        if (!isWorking()) {
            throw new ComponentIllegalStateException("Memory is not working :(");
        }
        if (!canRemoveMemory(memorySize)) {
            throw new RemoveMemoryException(
                    String.format("Free capacity is '%s'. Wanted to remove '%s' ", actualCapacity, memorySize));
        }
        actualCapacity -= memorySize;
    }

    @Override
    public float getPercentageUsage() {
        return actualCapacity / (float)totalCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Memory)) return false;
        if (!super.equals(o)) return false;
        Memory memory = (Memory) o;
        return totalCapacity == memory.totalCapacity &&
                actualCapacity == memory.actualCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalCapacity, actualCapacity);
    }

    @Override
    public String toString() {
        return "Memory{" +
                "totalCapacity=" + totalCapacity +
                ", actualCapacity=" + actualCapacity +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", dateOfMade=" + dateOfMade +
                ", expirationDate=" + expirationDate +
                ", isWorking=" + isWorking +
                '}';
    }
}
