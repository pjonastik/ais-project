package org.example;

import org.example.product.computer.Computer;
import org.example.product.memory.Memorable;
import org.example.product.memory.SdCard;
import org.example.product.memory.SsdDisk;
import org.example.product.memory.UsbKey;


public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello AIS, this is my example of computer" );

        Memorable ssdDisk = new SsdDisk("Samsung", "X1", 4);
        Computer computer = new Computer("Lenovo", "T480", ssdDisk);
        computer.setNumberOfPreviousOwners(1);
        printComputerMemoryInfo(computer);

        System.out.println("Mounting USB key...");
        Memorable usbKey1 = new UsbKey("Keystone", "a1c", 2);
        computer.mount(usbKey1);
        printComputerMemoryInfo(computer);

        System.out.println("Mounting USB key 2...");
        Memorable usbKey2 = new UsbKey("Keystone", "a2c", 2);
        computer.mount(usbKey2);
        printComputerMemoryInfo(computer);

        System.out.println("Mounting SD card...");
        Memorable sdCard = new SdCard("SanDisk", "SDXC", 2);
        computer.mount(sdCard);
        printComputerMemoryInfo(computer);

        System.out.println("Use 10 MB of computer memory.");
        computer.useMemory(10);
        printComputerMemoryInfo(computer);

        System.out.println("Remove 5 MB of computer memory.");
        computer.removeMemory(5);
        printComputerMemoryInfo(computer);

        System.out.println("Unmount SD card and USB key.");
        computer.unmount(sdCard);
        computer.unmount(usbKey1);
        printComputerMemoryInfo(computer);

        System.out.println("Computer debug data: " + computer);
        System.out.println("SSD disk debug data: " + ssdDisk);
        System.out.println("USB key debug data: " + usbKey1);
        System.out.println("SD card debug data: " + sdCard);
    }

    private static void printComputerMemoryInfo(Computer computer) {
        System.out.println("+------------------------------------------------------------+");
        System.out.println(String.format("Usage of computer's memory is %3.2f %% [%d/%d] Mb.",
                computer.getPercentageUsage() ,computer.getActualCapacity(), computer.getTotalCapacity()));
        System.out.println("+------------------------------------------------------------+");
    }
}
