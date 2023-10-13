package org.example.product.memory;

public interface Memorable {

    int getTotalCapacity();
    int getActualCapacity();
    boolean canUseMemory(int memorySize);
    void useMemory(int memorySize);

    boolean canRemoveMemory(int memorySize);
    void removeMemory(int memorySize);
    float getPercentageUsage();

    boolean isMountable();
}
