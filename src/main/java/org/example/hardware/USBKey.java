package org.example.hardware;

import org.example.Workable;


public class USBKey extends HardwareParent implements Workable {
    private final String usbType;

    public USBKey(int celkovaKapacitaPameti, String usbType) {
        this.celkovaKapacitaPameti = celkovaKapacitaPameti;
        this.usbType = usbType;
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
