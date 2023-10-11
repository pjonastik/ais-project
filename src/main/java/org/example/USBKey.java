package org.example;

public class USBKey extends ElektronickeZarizeni implements Workable{
    private float celkovaKapacitaPameti;
    private String usbType;

    public USBKey(float celkovaKapacitaPameti, String usbType) {
        this.celkovaKapacitaPameti = celkovaKapacitaPameti;
        this.usbType = usbType;
    }

    public float getCelkovaKapacitaPameti() {
        return celkovaKapacitaPameti;
    }

    @Override
    public boolean isWorking() {
        if(this.jeFunkcni) {
            return true;
        }
        return false;
    }
}
