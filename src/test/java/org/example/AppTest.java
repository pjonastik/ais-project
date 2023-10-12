package org.example;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import org.example.hardware.Computer;
import org.example.hardware.SDCard;
import org.example.hardware.SSDDisk;
import org.example.hardware.USBKey;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void celkovaKapacitaVsechZarizeni()
    {
        SSDDisk ssdDisk = new SSDDisk(10);
        Computer computer = new Computer(ssdDisk);
        USBKey usbKey = new USBKey(10, "Type-C");
        SDCard sdCard = new SDCard(10);
        computer.mount(sdCard);
        computer.mount(usbKey);

        assertThat(computer.getTotalCapacity(), is(30));
        assertThat(computer.getActualCapacity(), is(30));
    }
}
