package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SsdDisk ssdDisk = new SsdDisk(6);
        UsbKey usbKey = new UsbKey(2);
        SdCard sdCard = new SdCard(2);

        Computer computer = new Computer(ssdDisk, usbKey, sdCard);

        computer.useMemory(ssdDisk, 6);
        computer.useMemory(usbKey, 2);
        computer.useMemory(sdCard, 2);

        computer.removeMemory(usbKey,2);
        computer.removeMemory(ssdDisk, 3);

        System.out.println(computer.getPercentageUsage());


    }
}
