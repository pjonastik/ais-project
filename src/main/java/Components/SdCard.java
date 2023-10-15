/*
celková kapacita pamäte
značka
model
dátum výroby
dátum expiracie
stav reprezentujúci funkčnsť (áno vs nie)
usb type (A,,B,C ...)

 */
public class SdCard extends AbstractComponent {
    private int numberOfPreviousOwners;
    public SdCard(String brand, String model, String dateOfManufacture, String dateOfExpiration, int numPreviousOwners, int memorySize, int usedMemory, boolean working) {
        super(brand, model, dateOfManufacture, dateOfExpiration, memorySize, usedMemory, working);
        this.numberOfPreviousOwners = numPreviousOwners;
    }
}
