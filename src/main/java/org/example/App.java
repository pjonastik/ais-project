package org.example;

import org.example.Memory.SdCard;
import org.example.Memory.SsdDisk;
import org.example.Memory.UsbKey;

/**
 * Computer musí mať nasledovné vlanosti: Validne vytovrený objekt Computer je taký ktorý - obsahuje aspon jeden objekt typu SsdDisk. - Objekty typu SdCard a UsbKey možu byť
 * “vložené” do objektu typu Computer aj pozdeji Implmenetuj metodu - Computer.mount a Computer.unmountnt- ktora umozni pripojit/odpojit SdCard a UsbKey do pocitataca. - Ak by sme
 * sa pokusili vlozit SsdDisk, vyhodi sa vynimka SsdDiskUnmoutableException. Implementuj metódy Computer.useMemory tak, - computer vybral miesto kde sa pouzije pamat. - Moze to byt
 * SsdDisk, UsbKey, SsdDisk…. V metodach Computer.useMemory, Computer.removeMemory, - skontrulujete najrpv, ci vsetky dostupne pamatove uloziska funkcne. - Ak je jedno zapojenych
 * zariadeni nefunkcne vyhod vynimku ComponentIllegalStateException V metode main demostruj spravnost chovania, tak ze vytvoris objekt Computer s jednym SsdDiskom a dvomi UsbKey
 * objektami a jednym SdCard . - Kapacita ssdDisku bude 4 MB. - Kapacita Usb klocov bude 2 MB a - kapacit SdCard bude 2MB. Zapln plnu pamat pocitaca 10MB Vymaz 5 MB Zobraz v
 * konzele percetualne vyuzitie pocitaca.
 */
public class App {

    public static void main(String[] args) throws ComputerException {
        UsbKey usbKey1 = new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B");
        UsbKey usbKey2 = new UsbKey(3, "Sony", "D", "25.12.2020", "06.03.2040", true, "B");

        Computer computer = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true),
                usbKey1);

        computer = computer.unmount(usbKey1);
        System.out.println(computer.getTotalMemoryCapacity());

        computer = computer.mount(usbKey2);
        System.out.println(computer.getTotalMemoryCapacity());

        computer.useMemory(8);
        computer.removeMemory(4);
        System.out.println(computer.getPerctentalUsage());
    }

}
