package org.example.hardware;

import org.example.Workable;


public class SSDDisk extends HardwareParent implements Workable {

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
}
