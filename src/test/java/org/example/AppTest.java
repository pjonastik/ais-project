package org.example;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.example.Memory.SdCard;
import org.example.Memory.SsdDisk;
import org.example.Memory.UsbKey;
import org.junit.Test;

/**
 * V unit testoch - over funkcnost vyssie uvedenych metod. - Vsetky vytvorene unit testy by mali prechadzat na zeleno.
 */
public class AppTest {

    @Test
    public void createValidComputer() {
        Computer computer1 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"));

        assertThat(computer1.isWorking(), is(true));

        Computer computer2 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true));

        assertThat(computer2.isWorking(), is(true));

        Computer computer3 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"));

        assertThat(computer3.isWorking(), is(true));
    }

    @Test
    public void createInvalidComputer() {
        Computer computer1 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", false, "B"));

        assertThat(computer1.isWorking(), is(false));

        Computer computer2 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, false),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"));

        assertThat(computer2.isWorking(), is(false));

        Computer computer3 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", false),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"));

        assertThat(computer3.isWorking(), is(false));

        Computer computer4 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, false,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"));

        assertThat(computer4.isWorking(), is(false));
    }

    @Test
    public void getMemoryCapacity() {
        Computer computer1 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"));

        assertThat(computer1.getTotalMemoryCapacity(), is(8));

        Computer computer2 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(1, "Sony", "D", "25.12.2020", "06.03.2040", 0, true));

        assertThat(computer2.getTotalMemoryCapacity(), is(5));
    }

    @Test
    public void useMemoryCapacity() throws ComputerException {
        Computer computer1 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"));

        computer1.useMemory(8);
        assertThat(computer1.actualMemory(), is(0));

        Computer computer2 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(1, "Sony", "D", "25.12.2020", "06.03.2040", 0, true),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"));

        computer2.useMemory(6);
        assertThat(computer2.actualMemory(), is(1));
    }

    @Test
    public void useToMuchMemoryCapacity() throws ComputerException {
        Computer computer1 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"));

        computer1.useMemory(10);
        //tady to chce odchytnout výjimku, ale už si nemůžu vzpomenout a musím jít uspat děti
    }

    @Test
    public void removeMemoryCapacity() throws ComputerException {
        Computer computer1 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"));

        computer1.useMemory(8);
        computer1.removeMemory(5);
        assertThat(computer1.getUsedMemory(), is(3));
    }

    @Test
    public void getPercentalUsage() throws ComputerException {
        Computer computer1 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"));

        computer1.useMemory(4);
        assertThat(computer1.getPerctentalUsage(), is(50F));
    }

    @Test
    public void mountMemory() {
        Computer computer1 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true));

        assertThat(computer1.getTotalMemoryCapacity(), is(6));

        assertThat(computer1.mount(new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"))
                .getTotalMemoryCapacity(), is(8));

    }

    @Test
    public void unmountMemory() throws ComputerException {
        Computer computer1 = new Computer("Dell", "2023", "11.05.2018", "20.12.2030", 0, true,
                new SsdDisk(4, "Dragon", "III.A", "15.02.2020", "06.03.2050", true),
                new SdCard(2, "Sony", "D", "25.12.2020", "06.03.2040", 0, true),
                new UsbKey(2, "Sony", "D", "25.12.2020", "06.03.2040", true, "B"));

        assertThat(computer1.getTotalMemoryCapacity(), is(8));

        assertThat(computer1.unmount(computer1.getUsbKey()).getTotalMemoryCapacity(), is(6));

    }
}
