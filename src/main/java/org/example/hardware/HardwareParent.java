package org.example.hardware;

import org.example.Workable;

import static java.lang.Math.round;

public class HardwareParent implements Workable {
    protected String znacka;
    protected String model;
    protected String datumVyroby;
    protected String datumExpirace;
    protected boolean jeFunkcni = true;
    protected int celkovaKapacitaPameti;
    protected int aktualniKapacitaPameti;
    protected int pocetPredeslychMajitelu;

    public int getTotalCapacity() {
        return celkovaKapacitaPameti;
    }

    public int getActualCapacity() {
        return aktualniKapacitaPameti;
    }

    public boolean canUseMemory(int memorySize) {
        if (memorySize <= getActualCapacity()){
            return true;
        }
        return false;
    }

    public void useMemory(int memorySize) {
        if (canUseMemory(memorySize)){
            aktualniKapacitaPameti -= memorySize;
        }
    }

    public boolean canRemoveMemory(int memorySize) {
        if (memorySize <= getActualCapacity()) {
            return true;
        }
        return false;
    }

    public void removeMemory(int memorySize) {
        if (canRemoveMemory(memorySize)) {
            aktualniKapacitaPameti += memorySize;
        }

    }

    public float getPercentageUsage() {
        return round((float)aktualniKapacitaPameti / (float)celkovaKapacitaPameti * 100);
    }

    @Override
    public boolean isWorking() {
        if(this.jeFunkcni) {
            return true;
        }
        return false;
    }
}
