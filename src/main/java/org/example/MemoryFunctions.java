package org.example;

public interface MemoryFunctions {
    int getTotalCapacity();
    int getActualCapacity();
    boolean canUseMemory(int memorySize);
    void useMemory(int memorySize);
    boolean canRemoveMemory(int memorySize);
    void removeMemory(int memorySize);
    float getPercentageUsage();
}
