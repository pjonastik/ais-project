package org.example;

import org.example.hardware.Computer;
import org.example.hardware.SDCard;
import org.example.hardware.SSDDisk;
import org.example.hardware.USBKey;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SSDDisk ssdDisk1 = new SSDDisk(4);
        USBKey usbKey = new USBKey(2, "C");
        USBKey usbKey2 = new USBKey(2, "C");
        SDCard sdCard = new SDCard(2);
        Computer computer1 = new Computer(ssdDisk1);
        computer1.mount(usbKey);
        computer1.mount(usbKey2);
        computer1.mount(sdCard);

        computer1.getTotalCapacity();
        computer1.useMemory(4);
        computer1.useMemory(2);
        computer1.useMemory(2);
        computer1.removeMemory(4);
        computer1.removeMemory(1);
        computer1.getActualCapacity();
        System.out.println("Procentualni vyuziti pameti: " + computer1.getPercentageUsage());
        int i = 1;
    }
}
