package org.example.hardware;

import org.example.Workable;


public class SDCard extends HardwareParent implements Workable {

    public SDCard(int celkovaKapacitaPameti) {
        this.celkovaKapacitaPameti = celkovaKapacitaPameti;
        this.aktualniKapacitaPameti = celkovaKapacitaPameti;
    }

    public SDCard(int celkovaKapacitaPameti, int pocetPredeslychMajitelu) {
        this.celkovaKapacitaPameti = celkovaKapacitaPameti;
        this.aktualniKapacitaPameti = celkovaKapacitaPameti;
        this.pocetPredeslychMajitelu = pocetPredeslychMajitelu;
    }

    public int getPocetPredeslychMajitelu() {
        return pocetPredeslychMajitelu;
    }

    @Override
    public boolean isWorking() {
        if(this.jeFunkcni) {
            return true;
        }
        return false;
    }
}
