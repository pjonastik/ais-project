import Components.ComponentIllegalStateException;
import Components.NotEnoughMemoryException;
import Components.SsdDisk;
import org.junit.jupiter.api.Test;

public class ComponentTest {

    @Test
    void createSsd() throws ComponentIllegalStateException, NotEnoughMemoryException{
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                4096,
                0,
                true);
        assert ssdDisk.brand.equals("Intel");
        assert ssdDisk.model.equals("XXX");
        assert ssdDisk.isWorkable();
        assert ssdDisk.getTotalCapacity() == 4096;
        assert ssdDisk.getActualCapacity() == 4096;
        assert ssdDisk.canUseMemory(500);
        assert ssdDisk.canUseMemory(4096);
        assert !ssdDisk.canUseMemory(5000);
        assert !ssdDisk.canRemoveMemory(1);
        assert ssdDisk.getPercentageUsage() == 0f;
    }
    @Test
    void add500mb() throws ComponentIllegalStateException, NotEnoughMemoryException{
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                5000,
                0,
                true);
        assert ssdDisk.getTotalCapacity() == 5000;
        assert ssdDisk.getActualCapacity() == 5000;
        assert ssdDisk.canUseMemory(500);
        ssdDisk.useMemory(500);
        assert ssdDisk.getActualCapacity() == 4500;
        assert ssdDisk.canRemoveMemory(500);
        assert !ssdDisk.canRemoveMemory(5000);
        assert ssdDisk.getPercentageUsage() == 10f;
    }

    @Test
    void addAndRemove500mb() throws ComponentIllegalStateException, NotEnoughMemoryException {
        SsdDisk ssdDisk = new SsdDisk("Intel",
                "XXX",
                "10.05.2023",
                "10.05.2033",
                0,
                5000,
                0,
                true);
        assert ssdDisk.getTotalCapacity() == 5000;
        assert ssdDisk.getActualCapacity() == 5000;
        assert ssdDisk.canUseMemory(500);
        ssdDisk.useMemory(500);
        assert ssdDisk.getActualCapacity() == 4500;
        assert ssdDisk.canRemoveMemory(500);
        assert !ssdDisk.canRemoveMemory(5000);
        assert ssdDisk.getPercentageUsage() == 10f;
        ssdDisk.removeMemory(250);
        assert ssdDisk.getActualCapacity() == 4750;
        assert ssdDisk.canRemoveMemory(250);
        assert !ssdDisk.canRemoveMemory(500);
        assert ssdDisk.getPercentageUsage() == 5f;
        ssdDisk.removeMemory(250);
        assert ssdDisk.getTotalCapacity() == 5000;
        assert ssdDisk.getActualCapacity() == 5000;
        assert ssdDisk.canUseMemory(500);
        assert !ssdDisk.canRemoveMemory(5000);
    }

}
