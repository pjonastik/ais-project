package org.example;

public class SSDDisk extends ElektronickeZarizeni implements Workable{
    float celkovaKapacitaPameti;

    public SSDDisk(float celkovaKapacitaPameti) {
        this.celkovaKapacitaPameti = celkovaKapacitaPameti;
    }

    @Override
    public boolean isWorking() {
        if(this.jeFunkcni) {
            return true;
        }
        return false;
    }
}
