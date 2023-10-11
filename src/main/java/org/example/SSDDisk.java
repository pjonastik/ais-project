package org.example;

import static java.lang.Math.round;

public class SSDDisk extends ElektronickeZarizeni implements Workable, MemoryFunctions{

    public SSDDisk(int celkovaKapacitaPameti) {
        this.celkovaKapacitaPameti = celkovaKapacitaPameti;
        this.aktualniKapacitaPameti = celkovaKapacitaPameti;
    }

    @Override
    public boolean isWorking() {
        if(this.jeFunkcni) {
            return true;
        }
        return false;
    }

    @Override
    public int getTotalCapacity() {
        return celkovaKapacitaPameti;
    }

    @Override
    public int getActualCapacity() {
        return aktualniKapacitaPameti;
    }

    @Override
    public boolean canUseMemory(int memorySize) {
        if (memorySize <= getActualCapacity()){
            return true;
        }
        return false;
    }

    @Override
    public void useMemory(int memorySize) {
        if (canUseMemory(memorySize)){
            aktualniKapacitaPameti -= memorySize;
        }
    }

    @Override
    public boolean canRemoveMemory(int memorySize) {
        if (memorySize <= getActualCapacity()) {
            return true;
        }
        return false;
    }

    @Override
    public void removeMemory(int memorySize) {
        if (canRemoveMemory(memorySize)) {
            aktualniKapacitaPameti += memorySize;
        }

    }

    @Override
    public float getPercentageUsage() {
        return round((float)aktualniKapacitaPameti / (float)celkovaKapacitaPameti * 100);
    }
}
