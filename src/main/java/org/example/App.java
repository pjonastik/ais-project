package org.example;

/**
 Computer musí mať nasledovné vlanosti:
 Validne vytovrený objekt Computer je taký ktorý
 - obsahuje aspon jeden objekt typu SsdDisk.
 - Objekty typu SdCard a UsbKey možu byť “vložené” do objektu typu Computer aj pozdeji
 Implmenetuj metodu
 - Computer.mount a Computer.unmountnt- ktora umozni pripojit/odpojit SdCard a UsbKey do pocitataca.
    - Ak by sme sa pokusili vlozit SsdDisk, vyhodi sa vynimka SsdDiskUnmoutableException.
 Kazde pamatove medium (SsdDisk, SdCard, UsbKey) a Computer bude poskytovat metody
 - getTotalCapacity():int	     			// MB
 - getActualCapacity():int			// MB
 - canUseMemory(int memorySize): boolean
 - useMemory(int memorySize) 			// MB
 - canRemoveMemory(int memorySize)        	// MB
 - removeMomory(int memorySize)
 - hasEnoughCapacityFor(int memorySize): boolean
 - getPerctentalUsage()
 - aktualne vyuzitie pamate v perenctach
 Implementuj metódy Computer.useMemory tak,
 - computer vybral miesto kde sa pouzije pamat.
 - Moze to byt SsdDisk, UsbKey, SsdDisk….
 V metodach Computer.useMemory, Computer.removeMemory,
 - skontrulujete najrpv, ci vsetky dostupne pamatove uloziska funkcne.
 - Ak je jedno zapojenych zariadeni nefunkcne vyhod vynimku ComponentIllegalStateException
 V unit testoch
 - over funkcnost vyssie uvedenych metod.
 - Vsetky vytvorene unit testy by mali prechadzat na zeleno.
 V metode main demostruj spravnost chovania, tak ze vytvoris objekt Computer s jednym SsdDiskom a dvomi UsbKey objektami a jednym SdCard .
 - Kapacita ssdDisku bude 4 MB.
 - Kapacita Usb klocov bude 2 MB a
 - kapacit SdCard bude 2MB.
 Zapln plnu pamat pocitaca 10MB
 Vymaz 5 MB
 Zobraz v konzele percetualne vyuzitie pocitaca.

 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
