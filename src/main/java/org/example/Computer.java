package org.example;

import java.util.Date;
import java.util.Objects;

/*
Validne vytovrený objekt Computer je taký ktorý
 - obsahuje aspon jeden objekt typu SsdDisk.
 - Objekty typu SdCard a UsbKey možu byť “vložené” do objektu typu Computer aj pozdeji

Implmenetuj metodu
 - Computer.mount a Computer.unmountnt- ktora umozni pripojit/odpojit SdCard a UsbKey do pocitataca.
    - Ak by sme sa pokusili vlozit SsdDisk, vyhodi sa vynimka SsdDiskUnmoutableException.
 */
public class Computer implements Workable{
    private String znacka;
    private String model;
    private Date datumVyroby;
    private Date datumExpirace;
    private int pocetPredeslychMajitelu;
    private Boolean funkcni;
    private SsdDisk ssdDisk;

    private SdCard sdCard;
    private UsbKey usbKey;
    private UsbKey usbKey2;



    public Computer(String znacka, String model, Date datumVyroby, Date datumExpirace, int pocetPredeslychMajitelu, Boolean funkcni, SsdDisk ssdDisk) {
        this(znacka,model, datumVyroby, datumExpirace, pocetPredeslychMajitelu, funkcni, ssdDisk, null, null, null);
    }

    public Computer(String znacka, String model, Date datumVyroby, Date datumExpirace, int pocetPredeslychMajitelu, Boolean funkcni, SsdDisk ssdDisk, SdCard sdCard) {
        this(znacka,model, datumVyroby, datumExpirace, pocetPredeslychMajitelu, funkcni, ssdDisk, sdCard, null, null);
    }

    public Computer(String znacka, String model, Date datumVyroby, Date datumExpirace, int pocetPredeslychMajitelu, Boolean funkcni, SsdDisk ssdDisk, UsbKey usbKey) {
        this(znacka,model, datumVyroby, datumExpirace, pocetPredeslychMajitelu, funkcni, ssdDisk, null, usbKey, null);
    }

    public Computer(String znacka, String model, Date datumVyroby, Date datumExpirace, int pocetPredeslychMajitelu, Boolean funkcni, SsdDisk ssdDisk, SdCard sdCard,
            UsbKey usbKey) {
        this(znacka,model, datumVyroby, datumExpirace, pocetPredeslychMajitelu, funkcni, ssdDisk, sdCard, usbKey, null);
    }

    public Computer(String znacka, String model, Date datumVyroby, Date datumExpirace, int pocetPredeslychMajitelu, Boolean funkcni, SsdDisk ssdDisk, SdCard sdCard,
            UsbKey usbKey, UsbKey usbKey2) {
        this.znacka = znacka;
        this.model = model;
        this.datumVyroby = datumVyroby;
        this.datumExpirace = datumExpirace;
        this.pocetPredeslychMajitelu = pocetPredeslychMajitelu;
        this.funkcni = funkcni;
        this.ssdDisk = ssdDisk;
        this.sdCard = sdCard;
        this.usbKey = usbKey;
        this.usbKey2 = usbKey2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Computer)) {
            return false;
        }
        Computer computer = (Computer) o;
        return pocetPredeslychMajitelu == computer.pocetPredeslychMajitelu && Objects.equals(znacka, computer.znacka) && Objects.equals(model, computer.model)
                && Objects.equals(datumVyroby, computer.datumVyroby) && Objects.equals(datumExpirace, computer.datumExpirace) && Objects.equals(funkcni,
                computer.funkcni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(znacka, model, datumVyroby, datumExpirace, pocetPredeslychMajitelu, funkcni);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "znacka='" + znacka + '\'' +
                ", model='" + model + '\'' +
                ", datumVyroby=" + datumVyroby +
                ", datumExpirace=" + datumExpirace +
                ", pocetPredeslychMajitelu=" + pocetPredeslychMajitelu +
                ", funkcni=" + funkcni +
                '}';
    }

    @Override
    public Boolean isWorking() {
        return null;
    }

    public void setSdCard(SdCard sdCard) {
        this.sdCard = sdCard;
    }

    public void setUsbKey(UsbKey usbKey) {
        this.usbKey = usbKey;
    }

    public void setUsbKey2(UsbKey usbKey2) {
        this.usbKey2 = usbKey2;
    }
}
