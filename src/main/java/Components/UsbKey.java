/*
celková kapacita pamäte
značka
model
dátum výroby
dátum expiracie
stav reprezentujúci funkčnsť (áno vs nie)
usb type (A,,B,C ...)
 */
public class UsbKey extends AbstractComponent {
    private UsbType usbType;
    public UsbKey(String brand, String model, String dateOfManufacture, String dateOfExpiration, int memorySize, int usedMemory, boolean working) {
        super(brand, model, dateOfManufacture, dateOfExpiration, memorySize, usedMemory, working);
        this.usbType = usbType;
    }
}


