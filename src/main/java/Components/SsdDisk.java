/*
Vytvor triedu Computer s nasledovnými atribútmi:
značka
model
dátum výroby
dátum expiracie
počet predošlých majiteľov
stav reprezentujúci funkčnsť (áno vs nie)

 */
public class SsdDisk extends AbstractComponent {

    public SsdDisk(String brand, String model, String dateOfManufacture, String dateOfExpiration, int numPreviousOwners, int memorySize, int usedMemory, boolean working) {
        super(brand, model, dateOfManufacture, dateOfExpiration, memorySize, usedMemory, working);
    }
}
