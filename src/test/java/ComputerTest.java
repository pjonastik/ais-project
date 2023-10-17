import Components.ComponentIllegalStateException;
import Components.Computer;
import Components.NotEnoughMemoryException;
import Components.SsdDisk;
import Components.SsdDiskUnmoutableException;
import Components.UsbKey;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class ComputerTest {

    @Test
    void computerCreated(){
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4,
                0,
                true);

        Computer computer = new Computer("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
                true,
                ssdDisk);

        assert computer.getActualCapacity() == 4;
        assert computer.getTotalCapacity() == 4;
        assert computer.isWorkable();
    }

    @Test
    void computerFillMemorySingleSSD() throws ComponentIllegalStateException, NotEnoughMemoryException {
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4,
                0,
                true);

        Computer computer = new Computer("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
                true,
                ssdDisk);

        assert computer.getTotalCapacity() == 4;
        assert computer.isWorkable();
        computer.useMemory(2);
        assert computer.getActualCapacity() == 2;
        assert computer.getPercentageUsage() == 50f;
    }

    @Test
    void computerFillMemoryTwoDisks() throws ComponentIllegalStateException, NotEnoughMemoryException, SsdDiskUnmoutableException {
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4,
                0,
                true);

        UsbKey usbKey1 = new UsbKey("Intel",
                "YYY",
                "10.05.2023",
                "10.05.2033",
                2,
                0,
                true);

        Computer computer = new Computer("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
                true,
                ssdDisk);
        computer.mount(usbKey1);
        assert computer.getTotalCapacity() == 6;
        assert computer.getActualCapacity() == 6;
        assert computer.isWorkable();
        computer.useMemory(3);
        assert computer.getActualCapacity() == 3;
        assert computer.getPercentageUsage() == 50f;
    }
    @Test
    void computerFillMemoryTwoDisksOverFillOne() throws ComponentIllegalStateException, NotEnoughMemoryException, SsdDiskUnmoutableException {
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4,
                0,
                true);

        UsbKey usbKey1 = new UsbKey("Intel",
                "YYY",
                "10.05.2023",
                "10.05.2033",
                6,
                0,
                true);

        Computer computer = new Computer("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
                true,
                ssdDisk);
        computer.mount(usbKey1);
        assert computer.getTotalCapacity() == 10;
        assert computer.getActualCapacity() == 10;
        assert computer.isWorkable();
        computer.useMemory(5);
        assert computer.getActualCapacity() == 5;
        assert computer.getPercentageUsage() == 50f;
    }

    @Test
    void computerFillAndRemoveSingleSSD() throws ComponentIllegalStateException, NotEnoughMemoryException {
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4,
                0,
                true);

        Computer computer = new Computer("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
                true,
                ssdDisk);

        assert computer.getTotalCapacity() == 4;
        assert computer.isWorkable();
        computer.useMemory(2);
        assert computer.getActualCapacity() == 2;
        assert computer.getPercentageUsage() == 50f;
        computer.removeMemory(1);
        assert computer.getActualCapacity() == 3;
        assert computer.getPercentageUsage() == 25f;
    }

    @Test
    void computerFillAndRemoveTwoDisks() throws ComponentIllegalStateException, NotEnoughMemoryException, SsdDiskUnmoutableException {
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4,
                0,
                true);

        UsbKey usbKey1 = new UsbKey("Intel",
                "YYY",
                "10.05.2023",
                "10.05.2033",
                6,
                0,
                true);

        Computer computer = new Computer("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
                true,
                ssdDisk);
        computer.mount(usbKey1);
        assert computer.getTotalCapacity() == 10;
        assert computer.getActualCapacity() == 10;
        assert computer.isWorkable();
        computer.useMemory(2);
        assert computer.getActualCapacity() == 8;
        assert computer.getPercentageUsage() == 20f;
        computer.removeMemory(2);
        assert computer.getActualCapacity() == 10;
        assert computer.getPercentageUsage() == 0f;
    }

    @Test
    void computerFillAndRemoveTwoDisksWithOverfill() throws ComponentIllegalStateException, NotEnoughMemoryException, SsdDiskUnmoutableException {
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4,
                0,
                true);

        UsbKey usbKey1 = new UsbKey("Intel",
                "YYY",
                "10.05.2023",
                "10.05.2033",
                6,
                0,
                true);

        Computer computer = new Computer("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
                true,
                ssdDisk);
        computer.mount(usbKey1);
        assert computer.getTotalCapacity() == 10;
        assert computer.getActualCapacity() == 10;
        assert computer.isWorkable();
        computer.useMemory(6);
        assert computer.getActualCapacity() == 4;
        assert computer.getPercentageUsage() == 60f;
        computer.removeMemory(4);
        assert computer.getActualCapacity() == 8;
        assert computer.getPercentageUsage() == 20f;
    }

    @Test
    void computerUnworkable() throws ComponentIllegalStateException, NotEnoughMemoryException, SsdDiskUnmoutableException {
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4,
                0,
                true);

        Computer computer = new Computer("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
                false,
                ssdDisk);
        assert !computer.isWorkable();
    }

    @Test
    void computerUnworkableDisk() throws ComponentIllegalStateException, NotEnoughMemoryException, SsdDiskUnmoutableException {
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4,
                0,
                true);

        UsbKey usbKey1 = new UsbKey("Intel",
                "YYY",
                "10.05.2023",
                "10.05.2033",
                6,
                0,
                false);

        Computer computer = new Computer("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
                true,
                ssdDisk);
        computer.mount(usbKey1);
        assert computer.getTotalCapacity() == 10;
        assert computer.getActualCapacity() == 10;
        assert computer.isWorkable();
        try{
            computer.useMemory(6);
        } catch (ComponentIllegalStateException e){
            assert true;
            return;
        }
        assert false;
    }

    @Test
    void computerNotEnoughMemory() throws ComponentIllegalStateException, NotEnoughMemoryException, SsdDiskUnmoutableException {
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4,
                0,
                true);

        UsbKey usbKey1 = new UsbKey("Intel",
                "YYY",
                "10.05.2023",
                "10.05.2033",
                6,
                0,
                true);

        Computer computer = new Computer("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
                true,
                ssdDisk);
        computer.mount(usbKey1);
        assert computer.getTotalCapacity() == 10;
        assert computer.getActualCapacity() == 10;
        assert computer.isWorkable();
        try{
            computer.useMemory(15);
        } catch (NotEnoughMemoryException e){
            assert true;
            return;
        }
        assert false;
    }

    @Test
    void computerNotEnoughMemoryToRemove() throws ComponentIllegalStateException, NotEnoughMemoryException, SsdDiskUnmoutableException {
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4,
                0,
                true);

        UsbKey usbKey1 = new UsbKey("Intel",
                "YYY",
                "10.05.2023",
                "10.05.2033",
                6,
                0,
                true);

        Computer computer = new Computer("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
                true,
                ssdDisk);
        computer.mount(usbKey1);
        assert computer.getTotalCapacity() == 10;
        assert computer.getActualCapacity() == 10;
        assert computer.isWorkable();
        computer.useMemory(5);
        try{
            computer.removeMemory(10);
        } catch (NotEnoughMemoryException e){
            assert true;
            return;
        }
        assert false;
    }

    @Test
    void computerNotEnoughMemoryToRemoveInTotal() throws ComponentIllegalStateException, NotEnoughMemoryException, SsdDiskUnmoutableException {
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4,
                0,
                true);

        Computer computer = new Computer("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
                true,
                ssdDisk);
        assert computer.getTotalCapacity() == 4;
        assert computer.getActualCapacity() == 4;
        assert computer.isWorkable();
        try{
            computer.removeMemory(5);
        } catch (NotEnoughMemoryException e){
            assert true;
            return;
        }
        assert false;
    }

}
