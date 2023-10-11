package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SSDDisk ssdDisk1 = new SSDDisk(5);
        USBKey usbKey = new USBKey(5, "C");
        SDCard sdCard = new SDCard(5);
        Computer computer1 = new Computer(ssdDisk1);
        computer1.mount(usbKey);
        computer1.mount(sdCard);

        computer1.getTotalCapacity();
        computer1.useMemory(2);
        computer1.getActualCapacity();
        computer1.getPercentageUsage();
        int i = 1;
    }
}
