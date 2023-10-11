package org.example;

public class SDCard extends ElektronickeZarizeni implements Workable{
    private float celkovaKapacitaPameti;
    int pocetPredeslychMajitelu;

    public SDCard(float celkovaKapacitaPameti) {
        this.celkovaKapacitaPameti = celkovaKapacitaPameti;
    }

    public SDCard(float celkovaKapacitaPameti, int pocetPredeslychMajitelu) {
        this.celkovaKapacitaPameti = celkovaKapacitaPameti;
        this.pocetPredeslychMajitelu = pocetPredeslychMajitelu;
    }

    public float getCelkovaKapacitaPameti() {
        return celkovaKapacitaPameti;
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
