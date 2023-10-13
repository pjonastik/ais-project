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

        Memorable ssdDisk = new SsdDisk("Samsung", "X1", 1000);
        Computer computer = new Computer("Lenovo", "T480", ssdDisk);
        computer.setNumberOfPreviousOwners(1);
        printComputerMemoryInfo(computer);

        System.out.println("Mounting USB key...");
        UsbKey usbKey = new UsbKey("Keystone", "a1c", 100);
        computer.mount(usbKey);
        printComputerMemoryInfo(computer);

        System.out.println("Mounting SD card...");
        SdCard sdCard = new SdCard("SanDisk", "SDXC", 100);
        computer.mount(sdCard);
        printComputerMemoryInfo(computer);

        System.out.println("Use 500 MB of computer memory.");
        computer.useMemory(500);
        printComputerMemoryInfo(computer);

        System.out.println("Remove 100 MB of computer memory.");
        computer.removeMemory(100);
        printComputerMemoryInfo(computer);

        System.out.println("Unmount SD card and USB key.");
        computer.unmount(sdCard);
        computer.unmount(usbKey);
        printComputerMemoryInfo(computer);

        System.out.println("Computer debug data: " + computer);
        System.out.println("SSD disk debug data: " + ssdDisk);
        System.out.println("USB key debug data: " + usbKey);
        System.out.println("SD card debug data: " + sdCard);
    }

    private static void printComputerMemoryInfo(Computer computer) {
        System.out.println("+------------------------------------------------------------+");
        System.out.println(String.format("Usage of computer's memory is \"%3.2f\" %% [%d/%d] Mb.",
                computer.getPercentageUsage() ,computer.getActualCapacity(), computer.getTotalCapacity()));
        System.out.println("+------------------------------------------------------------+");
    }
}
