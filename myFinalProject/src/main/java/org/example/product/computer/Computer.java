package org.example.product.computer;

import org.example.product.Ownerable;
import org.example.product.Product;
import org.example.product.computer.exception.SsdDiskUnmountableException;
import org.example.product.memory.Memorable;
import org.example.product.memory.exception.RemoveMemoryException;
import org.example.product.memory.exception.UseMemoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Computer extends Product implements Ownerable, Memorable {

    private final List<Memorable> memories;
    private int numberOfPreviousOwners;

    public Computer(String brand, String model, Memorable internalMemory) {
        super(brand, model);
        this.memories = new ArrayList<>();
        memories.add(internalMemory);
    }

    @Override
    public int numberOfPreviousOwners() {
        return numberOfPreviousOwners;
    }

    public void setNumberOfPreviousOwners(int numberOfPreviousOwners) {
        this.numberOfPreviousOwners = numberOfPreviousOwners;
    }

    @Override
    public int getTotalCapacity() {
        return memories.stream()
                .map(Memorable::getTotalCapacity)
                .reduce(0, Integer::sum);
    }

    @Override
    public int getActualCapacity() {
        return memories.stream()
                .map(Memorable::getActualCapacity)
                .reduce(0, Integer::sum);
    }

    @Override
    public int getFreeCapacity() {
        return getTotalCapacity() - getActualCapacity();
    }

    @Override
    public boolean canUseMemory(int memoryToBeUsed) {
        int freeCapacity = getTotalCapacity() - getActualCapacity();
        return (freeCapacity - memoryToBeUsed) >= 0;
    }

    @Override
    public void useMemory(int memoryToBeUsed) {
        if (getFreeCapacity() < memoryToBeUsed) {
            throw new UseMemoryException(
                String.format("Memory usage ['%s'/'%s']. Not enough space for '%s'.",
                        getActualCapacity(), getTotalCapacity(), memoryToBeUsed));
        }

        int restOfMemorySize = memoryToBeUsed;
        for (Memorable memory : memories) {
            if (memory.canUseMemory(restOfMemorySize)) {
                memory.useMemory(restOfMemorySize);
                break;
            } else {
                memory.useMemory(memory.getFreeCapacity());
                restOfMemorySize -= memory.getFreeCapacity();
            }
        }
    }

    @Override
    public boolean canRemoveMemory(int memoryToBeRemoved) {
        return memoryToBeRemoved <= getActualCapacity();
    }

    @Override
    public void removeMemory(int memoryToBeRemoved) {
        if (getActualCapacity() < memoryToBeRemoved) {
            throw new RemoveMemoryException(
                    String.format("Memory usage ['%s'/'%s']. Not enough used memory to remove space '%s' Mb.",
                            getActualCapacity(), getTotalCapacity(), memoryToBeRemoved));
        }

        int restOfMemorySize = memoryToBeRemoved;
        for (Memorable memory : memories) {
            if (memory.canRemoveMemory(restOfMemorySize)) {
                memory.removeMemory(restOfMemorySize);
                break;
            } else {
                restOfMemorySize -= memory.getActualCapacity();
                memory.removeMemory(memory.getActualCapacity());
            }
        }
    }

    @Override
    public float getPercentageUsage() {
        return (getActualCapacity() / (float)getTotalCapacity())*100;
    }

    @Override
    public boolean isMountable() {
        return false;
    }

    public void mount(Memorable memorable) {
        if (!memorable.isMountable()) {
//            if (memory instanceof SsdDisk) { //does not have to bee here since there is only one such exception
            throw new SsdDiskUnmountableException("You cannot mount this type of memory to computer! " + memorable );
//            }
        }
        memories.add(memorable);
    }

    public void unmount(Memorable memory) {
        if (!memory.isMountable()) {
//            if (memory instanceof SsdDisk) { //does not have to bee here since there is only one such exception
                throw new SsdDiskUnmountableException("You cannot unmount this type of memory from computer! " + memory );
//            }
        }

        Optional<Memorable> first = memories.stream()
                .filter(m -> m.equals(memory))
                .findFirst();

        if (first.isPresent()) {
            memories.remove(memory);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Computer)) return false;
        if (!super.equals(o)) return false;
        Computer computer = (Computer) o;
        return numberOfPreviousOwners == computer.numberOfPreviousOwners
                && Objects.equals(memories, computer.memories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), memories, numberOfPreviousOwners);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "memories=" + memories +
                ", numberOfPreviousOwners=" + numberOfPreviousOwners +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", dateOfMade=" + dateOfMade +
                ", expirationDate=" + expirationDate +
                ", isWorking=" + isWorking +
                '}';
    }
}
