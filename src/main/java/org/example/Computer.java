package org.example;

public class Computer extends ElektronickeZarizeni implements Workable{
    private int pocetPredeslychMajitelu;
    private SSDDisk ssdDisk;
    private SDCard sdCard;
    private USBKey usbKey;

    public Computer(SSDDisk ssdDisk) {
        this.ssdDisk = ssdDisk;
    }

    public Computer(int pocetPredeslychMajitelu, SSDDisk ssdDisk) {
        this.pocetPredeslychMajitelu = pocetPredeslychMajitelu;
        this.ssdDisk = ssdDisk;
    }

    public float getTotalCapacity(){
        return  this.ssdDisk.celkovaKapacitaPameti +
                this.sdCard.getCelkovaKapacitaPameti() +
                this.usbKey.getCelkovaKapacitaPameti();
    }

    public void mount(SDCard sdCard){
        this.sdCard = sdCard;
    }

    public void mount(USBKey usbKey){
        this.usbKey = usbKey;
    }

    public void unmount(SDCard sdCard){
        this.sdCard = null;
    }

    public void unmount(USBKey usbKey){
        this.usbKey = null;
    }

    @Override
    public boolean isWorking() {
        if(this.jeFunkcni) {
            return true;
        }
        return false;
    }
}
