package org.example;

import java.util.Objects;

public class Memory extends Hardware implements Workable{
    private double memoryCapacity;
    private double usedMemory;

    public Memory(double memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
        usedMemory = 0;
        this.isFunctional = true;
    }



    public double useMemory(double memorySize){
        if (!this.isFunctional) throw new ComponentIllegalStateException("Nefunkcni pamet");
        if (canUseMemory(memorySize)){
            this.usedMemory += memorySize;
        } else {
            throw new RuntimeException("Nedostatek Pameti");
        }
        return usedMemory;
    }

    public double removeMemory(double memorySize){
        if (!this.isFunctional) throw new ComponentIllegalStateException("Nefunkcni pamet");
        if (canRemoveMemory(memorySize)){
            this.usedMemory -= memorySize;
        } else {
            throw new RuntimeException("Nelze smazat vice.");
        }
        return usedMemory;
    }

    public double getTotalCapacity(){
        return this.memoryCapacity;
    }

    public double getActualCapacity(){
        return memoryCapacity - usedMemory;
    }

    public boolean canUseMemory(double memorySize){
        return (this.memoryCapacity - this.usedMemory) >= memorySize;
    }

    public boolean canRemoveMemory(double memorySize){
        return (this.usedMemory >= memorySize);
    }

    public double getPercentageUsage(){
        return (usedMemory / memoryCapacity) * 100;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "memoryCapacity=" + memoryCapacity +
                ", usedMemory=" + usedMemory +
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
        if (!(o instanceof Memory)) return false;
        Memory memory = (Memory) o;
        return Double.compare(memoryCapacity, memory.memoryCapacity) == 0 && Double.compare(usedMemory, memory.usedMemory) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memoryCapacity, usedMemory);
    }

    @Override
    public boolean isWorking() {
        return isFunctional;
    }
}
