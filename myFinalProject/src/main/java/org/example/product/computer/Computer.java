package org.example.product.computer;

import org.example.product.Ownerable;
import org.example.product.Product;
import org.example.product.memory.Memorable;
import org.example.product.memory.exception.RemoveMemoryException;
import org.example.product.memory.exception.UseMemoryException;

import java.util.ArrayList;
import java.util.List;
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
    public boolean canUseMemory(int memorySize) {
        int freeCapacity = getTotalCapacity() - getActualCapacity();
        return (freeCapacity - memorySize) >= 0;
    }


//    @Override
//    public void useMemory(int memorySize) {
//
//        Memorable memoryWithEnoughSpace = null;
//        for (Memorable memory : memories) {
//            if (memory.canUseMemory(memorySize)) {
//                memoryWithEnoughSpace = memory;
//            }
//        }
//
//        if (memoryWithEnoughSpace == null) {
//            throw new UseMemoryException(
//                    String.format("Memory usage ['%s'/'%s']. Not enough space for '%s'.",
//                            getActualCapacity(), getTotalCapacity(), memorySize));
//        }
//
//        memoryWithEnoughSpace.useMemory(memorySize);
//    }

    /**
     * Stream API implementation
     */
    @Override
    public void useMemory(int memorySize) {

        Optional<Memorable> memory = memories.stream()
                .filter(m -> m.canUseMemory(memorySize))
                .findFirst();


        Memorable memorable = memory.orElseThrow(() -> new UseMemoryException(
                String.format("Memory usage ['%s'/'%s']. Not enough space for '%s'.",
                        getActualCapacity(), getTotalCapacity(), memorySize)));

        memorable.useMemory(memorySize);
    }

    @Override
    public boolean canRemoveMemory(int memorySize) {
        return memorySize <= getActualCapacity();
    }

    @Override
    public void removeMemory(int memorySize) {
        Optional<Memorable> memory = memories.stream()
                .filter(m -> m.canRemoveMemory(memorySize))
                .findFirst();
        if (memory.isEmpty()) {
            throw new RemoveMemoryException(
                    String.format("Memory usage ['%s'/'%s']. Not enough used memory to remove space '%s' Mb.",
                            getActualCapacity(), getTotalCapacity(), memorySize));
        } else {
            memory.get().removeMemory(memorySize);
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
        memories.add(memorable);
    }

    public void unmount(Memorable memory) {
        if (!memory.isMountable()) {
            throw new UnmountMemoryException("You cannot unmount this type of memory from computer! " + memory );
        }

        Optional<Memorable> first = memories.stream()
                .filter(m -> m.equals(memory))
                .findFirst();

        if (first.isPresent()) {
            memories.remove(memory);
        }

    }
}
