package org.example;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.management.MemoryPoolMXBean;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */

    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testMemory(){
        Memory memory = new Memory(100);
        Memory memory2 = memory;
        assertEquals(memory2,memory);
    }

    public void testGetTotalCapacity() {
        Memory memory = new Memory(100);
        assertEquals(100.0, memory.getTotalCapacity());
    }

    public void testGetActualCapacity(){
        Memory memory = new Memory(100);
        assertEquals(100.0, memory.getActualCapacity());
    }

    public void testGetActualCapacityUsedMemory(){
        Memory memory = new Memory(100);
        memory.useMemory(10);
        assertEquals(90.0, memory.getActualCapacity());
    }

    public void testIsWorking(){
        Memory memory = new Memory(100);
        assertEquals(true, memory.isWorking());
    }

    public void testUseMemory(){
        Memory memory = new Memory(100);
        assertEquals(10.0, memory.useMemory(10));
    }

    public void testUseMemoryNotEnoughMemory(){
        Memory memory = new Memory(100);
        boolean thrown = false;
        try {
            memory.useMemory(110);
        } catch (RuntimeException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    public void testUseMemoryNotFunctional(){
        Memory memory = new Memory(100);
        memory.setFunctional(false);
        boolean thrown = false;
        try {
            memory.useMemory(10);
        } catch (ComponentIllegalStateException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    public void testRemoveMemory(){
        Memory memory = new Memory(100);
        memory.useMemory(10);
        assertEquals(0.0, memory.removeMemory(10));
    }

    public void testRemoveMemoryNotEnoughMemory(){
        Memory memory = new Memory(100);
        boolean thrown = false;
        try {
            memory.removeMemory(10);
        } catch (RuntimeException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    public void testRemoveMemoryNotFunctional(){
        Memory memory = new Memory(100);
        memory.setFunctional(false);
        boolean thrown = false;
        try {
            memory.removeMemory(10);
        } catch (ComponentIllegalStateException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    public void testCanUseMemory(){
        Memory memory = new Memory(100);
        assertEquals(true,memory.canUseMemory(10));
    }

    public void testCanUseMemoryFalse(){
        Memory memory = new Memory(100);
        assertEquals(false,memory.canUseMemory(110));
    }

    public void testRemoveUseMemoryFalse(){
        Memory memory = new Memory(100);
        assertEquals(false,memory.canRemoveMemory(10));
    }

    public void testRemoveUseMemory(){
        Memory memory = new Memory(100);
        memory.useMemory(10);
        assertEquals(true,memory.canRemoveMemory(10));
    }

    public void testGetPercentageUsage(){
        Memory memory = new Memory(100);
        assertEquals(0.0,memory.getPercentageUsage());
    }

    public void testGetPercentageUsageUsedMemory(){
        Memory memory = new Memory(100);
        memory.useMemory(10);
        assertEquals(10.0,memory.getPercentageUsage());
    }

    public void testComputer(){
        SsdDisk ssdDisk = new SsdDisk(100);
        Computer computer = new Computer(ssdDisk);
        assertEquals(ssdDisk,computer.getSsdDisk());
    }

    public void testComputerComplete(){
        SsdDisk ssdDisk = new SsdDisk(100);
        SdCard sdCard = new SdCard(30);
        UsbKey usbKey = new UsbKey(10);

        Computer computer = new Computer(ssdDisk, usbKey, sdCard);

        assertEquals(ssdDisk, computer.getSsdDisk());
        assertEquals(usbKey, computer.getUsbKey());
        assertEquals(sdCard,computer.getSdCard());
    }

    public void testMountSdCard(){
        Computer computer = new Computer(new SsdDisk(100));
        SdCard sdCard = new SdCard(30);
        computer.mount(sdCard);
        assertEquals(sdCard, computer.getSdCard());
    }

    public void testMountUsbKey(){
        Computer computer = new Computer(new SsdDisk(100));
        UsbKey usbKey = new UsbKey(3);
        computer.mount(usbKey);
        assertEquals(usbKey, computer.getUsbKey());
    }

    public void testMountSsdDisk(){
        Computer computer = new Computer(new SsdDisk(100));
        SsdDisk ssdDisk = new SsdDisk(25);
        boolean thrown = false;
        try {
            computer.mount(ssdDisk);
        } catch (SsdDiskUnmoutableException exception) {
            thrown = true;
        }
        assertTrue(thrown);
    }

}
