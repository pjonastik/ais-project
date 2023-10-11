package org.example;

public class ElektronickeZarizeni implements Workable{
    protected String znacka;
    protected String model;
    protected String datumVyroby;
    protected String datumExpirace;
    protected boolean jeFunkcni;



    @Override
    public boolean isWorking() {
        if(this.jeFunkcni) {
            return true;
        }
        return false;
    }
}
