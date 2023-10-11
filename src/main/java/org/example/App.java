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
        SSDDisk ssdDisk1 = new SSDDisk(4);
        Computer computer1 = new Computer(ssdDisk1);
        computer1.getTotalCapacity();
        int i = 1;
    }
}
