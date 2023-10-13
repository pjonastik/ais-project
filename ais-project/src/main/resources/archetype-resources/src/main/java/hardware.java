import java.util.Date;

class ComponentIllegalStateException extends Exception {
    public ComponentIllegalStateException(String message) {
        super(message);
    }
}

interface Workable {
    boolean isWorking();
}

class Computer implements Workable {
    private String brand;
    private String model;
    private Date manufacturingDate;
    private Date expirationDate;
    private int previousOwners;
    private boolean working;
    private SsdDisk ssdDisk;
    private UsbKey usbKey;
    private SdCard sdCard;

    public Computer(String brand, String model, Date manufacturingDate, Date expirationDate, int previousOwners, boolean working) {
        this.brand = brand;
        this.model = model;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.previousOwners = previousOwners;
        this.working = working;
    }

    public void mount(SdCard sdCard) {
        this.sdCard = sdCard;
    }

    public void mount(UsbKey usbKey) {
        this.usbKey = usbKey;
    }

    public void unmount() {
        sdCard = null;
        usbKey = null;
    }

    public void mount(SsdDisk ssdDisk) throws SsdDiskUnmoutableException {
        if (isWorking()) {
            this.ssdDisk = ssdDisk;
        } else {
            throw new SsdDiskUnmoutableException("Computer is not working. Cannot mount SsdDisk.");
        }
    }

    public void unmountSsdDisk() {
        ssdDisk = null;
    }

    public int getTotalCapacity() {
        int totalCapacity = 0;
        if (ssdDisk != null) {
            totalCapacity += ssdDisk.getTotalCapacity();
        }
        if (usbKey != null) {
            totalCapacity += usbKey.getTotalCapacity();
        }
        if (sdCard != null) {
            totalCapacity += sdCard.getTotalCapacity();
        }
        return totalCapacity;
    }

    public int getActualCapacity() {
        int actualCapacity = 0;
        if (ssdDisk != null) {
            actualCapacity += ssdDisk.getActualCapacity();
        }
        if (usbKey != null) {
            actualCapacity += usbKey.getActualCapacity();
        }
        if (sdCard != null) {
            actualCapacity += sdCard.getActualCapacity();
        }
        return actualCapacity;
    }

    public boolean canUseMemory(int memorySize) {
        return (getActualCapacity() + memorySize <= getTotalCapacity());
    }

    public double percentageUsage() {
        if (getTotalCapacity() == 0) {
            return 0;
        }
        return (double) getActualCapacity() / getTotalCapacity() * 100;
    }

    public boolean isWorking() {
        return working;
    }

    public void useMemory(int memorySize) throws ComponentIllegalStateException {
        if (isWorking()) {
            if (ssdDisk != null && ssdDisk.isWorking()) {
                ssdDisk.useMemory(memorySize);
            } else if (usbKey != null && usbKey.isWorking()) {
                usbKey.useMemory(memorySize);
            } else if (sdCard != null && sdCard.isWorking()) {
                sdCard.useMemory(memorySize);
            } else {
                throw new ComponentIllegalStateException("No functional memory storage available.");
            }
        } else {
            throw new ComponentIllegalStateException("Computer is not working.");
        }
    }

    public void removeMemory(int memorySize) throws ComponentIllegalStateException {
        if (isWorking()) {
            if (ssdDisk != null && ssdDisk.isWorking()) {
                ssdDisk.removeMemory(memorySize);
            } else if (usbKey != null && usbKey.isWorking()) {
                usbKey.removeMemory(memorySize);
            } else if (sdCard != null && sdCard.isWorking()) {
                sdCard.removeMemory(memorySize);
            } else {
                throw new ComponentIllegalStateException("No functional memory storage available.");
            }
        } else {
            throw new ComponentIllegalStateException("Computer is not working.");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, manufacturingDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return Objects.equals(brand, computer.brand) &&
                Objects.equals(model, computer.model) &&
                Objects.equals(manufacturingDate, computer.manufacturingDate);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", manufacturingDate=" + manufacturingDate +
                ", expirationDate=" + expirationDate +
                ", previousOwners=" + previousOwners +
                ", working=" + working +
                ", ssdDisk=" + ssdDisk +
                ", usbKey=" + usbKey +
                ", sdCard=" + sdCard +
                '}';
    }
}

class MemoryMedium implements Workable {
    private int totalCapacity;
    private String brand;
    private String model;
    private Date manufacturingDate;
    private Date expirationDate;
    private boolean working;
    private int actualCapacity;

    public MemoryMedium(int totalCapacity, String brand, String model, Date manufacturingDate, Date expirationDate, boolean working) {
        this.totalCapacity = totalCapacity;
        this.brand = brand;
        this.model = model;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.working = working;
        this.actualCapacity = totalCapacity;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getActualCapacity() {
        return actualCapacity;
    }

    public boolean canUseMemory(int memorySize) {
        return (actualCapacity - memorySize >= 0);
    }

    public void useMemory(int memorySize) {
        if (canUseMemory(memorySize)) {
            actualCapacity -= memorySize;
        }
    }

    public boolean canRemoveMemory(int memorySize) {
        return (actualCapacity + memorySize <= totalCapacity);
    }

    public void removeMemory(int memorySize) {
        if (canRemoveMemory(memorySize)) {
            actualCapacity += memorySize;
        }
    }

    public double percentageUsage() {
        if (totalCapacity == 0) {
            return 0;
        }
        return (double) (totalCapacity - actualCapacity) / totalCapacity * 100;
    }

    public boolean isWorking() {
        return working;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCapacity, brand, model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemoryMedium that = (MemoryMedium) o;
        return totalCapacity == that.totalCapacity &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(model, that.model);
    }

    @Override
    public String toString() {
        return "MemoryMedium{" +
                "totalCapacity=" + totalCapacity +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}

class UsbKey extends MemoryMedium {
    private String usbType;

    public UsbKey(int totalCapacity, String brand, String model, Date manufacturingDate, Date expirationDate, boolean working, String usbType) {
        super(totalCapacity, brand, model, manufacturingDate, expirationDate, working);
        this.usbType = usbType;
    }

    public String getUsbType() {
        return usbType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), usbType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UsbKey usbKey = (UsbKey) o;
        return Objects.equals(usbType, usbKey.usbType);
    }

    @Override
    public String toString() {
        return "UsbKey{" +
                "usbType='" + usbType + '\'' +
                "} " + super.toString();
    }
}

class SsdDisk extends MemoryMedium {
    public SsdDisk(int totalCapacity, String brand, String model, Date manufacturingDate, Date expirationDate, boolean working) {
        super(totalCapacity, brand, model, manufacturingDate, expirationDate, working);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        return true;
    }

    @Override
    public String toString() {
        return "SsdDisk{} " + super.toString();
    }
}

class SdCard extends MemoryMedium {
    private int previousOwners;

    public SdCard(int totalCapacity, String brand, String model, Date manufacturingDate, Date expirationDate, int previousOwners, boolean working) {
        super(totalCapacity, brand, model, manufacturingDate, expirationDate, working);
        this.previousOwners = previousOwners;
    }

    public int getPreviousOwners() {
        return previousOwners;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), previousOwners);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SdCard sdCard = (SdCard) o;
        return previousOwners == sdCard.previousOwners;
    }

    @Override
    public String toString() {
        return "SdCard{" +
                "previousOwners=" + previousOwners +
                "} " + super.toString();
    }
}

public class ComputerTest {

    @Test
    public void testUseMemory() throws ComponentIllegalStateException {
        Computer computer = createComputerWithMemoryDevices();

        computer.useMemory(3);
        assertEquals(1, computer.getSsdDisk().getActualCapacity());
        assertEquals(2, computer.getUsbKey().getActualCapacity());
        assertEquals(2, computer.getSdCard().getActualCapacity());

        computer.useMemory(5);
        assertEquals(0, computer.getSsdDisk().getActualCapacity());
        assertEquals(0, computer.getUsbKey().getActualCapacity());
        assertEquals(1, computer.getSdCard().getActualCapacity());
    }

    @Test
    public void testRemoveMemory() throws ComponentIllegalStateException {
        Computer computer = createComputerWithMemoryDevices();

        computer.removeMemory(2);
        assertEquals(2, computer.getSsdDisk().getActualCapacity());
        assertEquals(2, computer.getUsbKey().getActualCapacity());
        assertEquals(2, computer.getSdCard().getActualCapacity());

        computer.removeMemory(4);
        assertEquals(4, computer.getSsdDisk().getActualCapacity());
        assertEquals(2, computer.getUsbKey().getActualCapacity());
        assertEquals(2, computer.getSdCard().getActualCapacity());
    }

    @Test
    public void testPercentageUsage() throws ComponentIllegalStateException {
        Computer computer = createComputerWithMemoryDevices();
        computer.useMemory(6);

        double usage = computer.percentageUsage();

        assertEquals(60.0, usage, 0.01);
    }

    public static void main(String[] args) throws ComponentIllegalStateException {
        Computer computer = createComputerWithMemoryDevices();
        computer.useMemory(10);
        computer.removeMemory(5);

        double usage = computer.percentageUsage();
        System.out.println("Percentage Usage: " + usage + "%");
    }

    private static Computer createComputerWithMemoryDevices() {
        SsdDisk ssdDisk = new SsdDisk(4, "Samsung", "SSD123", new Date(), new Date(), true);
        UsbKey usbKey = new UsbKey(2, "Kingston", "USB456", new Date(), new Date(), true, "A");
        SdCard sdCard = new SdCard(2, "SanDisk", "SD789", new Date(), new Date(), 1, true);

        Computer computer = new Computer("Dell", "XPS", new Date(), new Date(), 0, true);
        computer.mount(ssdDisk);
        computer.mount(usbKey);
        computer.mount(sdCard);

        return computer;
    }
}