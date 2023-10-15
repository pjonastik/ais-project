package org.example;

import java.util.Objects;
import org.example.Memory.SdCard;
import org.example.Memory.SsdDisk;
import org.example.Memory.UsbKey;

/*
Validne vytovrený objekt Computer je taký ktorý
 - obsahuje aspon jeden objekt typu SsdDisk.
 - Objekty typu SdCard a UsbKey možu byť “vložené” do objektu typu Computer aj pozdeji

Implmenetuj metodu
 - Computer.mount a Computer.unmountnt- ktora umozni pripojit/odpojit SdCard a UsbKey do pocitataca.
    - Ak by sme sa pokusili vlozit SsdDisk, vyhodi sa vynimka SsdDiskUnmoutableException.
 */
public class Computer implements Workable {

    private final String brand;
    private final String model;
    private final String manufactureDate;
    private final String expirationDate;
    private final int numberOfPreviousOwner;
    private final Boolean working;
    private final SsdDisk ssdDisk;

    private SdCard sdCard;
    private UsbKey usbKey;

    public float getPerctentalUsage() {
        return 100F / getTotalMemoryCapacity() * getUsedMemory();
    }

    public int getUsedMemory() {
        int usedMemory = this.ssdDisk.usedMemory;

        if (this.sdCard != null) {
            usedMemory = usedMemory + this.sdCard.usedMemory;
        }
        if (this.usbKey != null) {
            usedMemory = usedMemory + this.usbKey.usedMemory;
        }
        return usedMemory;
    }

    public int getTotalMemoryCapacity() {
        int totalMemory = this.ssdDisk.getTotalMemoryCapacity();

        if (this.sdCard != null) {
            totalMemory = totalMemory + this.sdCard.getTotalMemoryCapacity();
        }
        if (this.usbKey != null) {
            totalMemory = totalMemory + this.usbKey.getTotalMemoryCapacity();
        }
        return totalMemory;

    }

    public int actualMemory() {
        int actualMemoryCapacity = this.ssdDisk.getActualMemoryCapacity();

        if (this.sdCard != null) {
            actualMemoryCapacity = actualMemoryCapacity + this.sdCard.getActualMemoryCapacity();
        }
        if (this.usbKey != null) {
            actualMemoryCapacity = actualMemoryCapacity + this.usbKey.getActualMemoryCapacity();
        }
        return actualMemoryCapacity;
    }

    public void useMemory(int memoryToUse) throws ComputerException {
        if (isWorking()) {
            if (actualMemory() >= memoryToUse) {
                if (ssdDisk.canUseMemory(memoryToUse)) {
                    ssdDisk.useMemory(memoryToUse);
                } else {
                    ssdDisk.useMemory(ssdDisk.getActualMemoryCapacity());
                    memoryToUse = memoryToUse - ssdDisk.getActualMemoryCapacity();
                    if (sdCard.canUseMemory(memoryToUse)) {
                        sdCard.useMemory(memoryToUse);
                    } else {
                        sdCard.useMemory(sdCard.getActualMemoryCapacity());
                        memoryToUse = memoryToUse - sdCard.getActualMemoryCapacity();
                        usbKey.useMemory(memoryToUse);
                    }
                }
            } else {
                throw new ComputerException("Not enought of memory");
            }
        } else {
            throw new ComputerException("ComponentIllegalStateException");
        }
    }

    public void removeMemory(int memoryToRemove) throws ComputerException {
        if (isWorking()) {
            if (getUsedMemory() > memoryToRemove) {
                if (memoryToRemove >= 0) {
                    if (ssdDisk.canRemoveMemory(memoryToRemove)) {
                        ssdDisk.removeMemory(memoryToRemove);
                    } else {
                        ssdDisk.removeMemory(ssdDisk.getTotalMemoryCapacity());
                        memoryToRemove = memoryToRemove - ssdDisk.getTotalMemoryCapacity();
                        if (sdCard.canRemoveMemory(memoryToRemove)) {
                            sdCard.removeMemory(memoryToRemove);
                        } else {
                            sdCard.removeMemory(sdCard.getTotalMemoryCapacity());
                            memoryToRemove = memoryToRemove - getSdCard().getTotalMemoryCapacity();
                            usbKey.removeMemory(memoryToRemove);
                        }
                    }
                } else {
                    throw new ComputerException("You cannot remove less than 0 memory");
                }
            } else {
                throw new ComputerException("You don't use so much memory");
            }
        } else {
            throw new ComputerException("ComponentIllegalStateException");
        }
    }

    public Computer mount(SdCard sdCard) {
        return mount(sdCard, null);
    }

    public Computer mount(UsbKey usbKey) {
        return mount(null, usbKey);
    }

    public Computer mount(SdCard sdCard, UsbKey usbKey) {
        if (usbKey == null) {
            return new Computer(this.brand, this.model, this.manufactureDate, this.expirationDate, this.numberOfPreviousOwner, this.working, this.ssdDisk, sdCard, this.usbKey);
        } else {
            if (sdCard == null) {
                return new Computer(this.brand, this.model, this.manufactureDate, this.expirationDate, this.numberOfPreviousOwner, this.working, this.ssdDisk, this.sdCard, usbKey);
            } else {
                return new Computer(this.brand, this.model, this.manufactureDate, this.expirationDate, this.numberOfPreviousOwner, this.working, this.ssdDisk, sdCard, usbKey);
            }
        }
    }

    public Computer unmount(SdCard sdCard) throws ComputerException {
        return unmount(sdCard, null);
    }

    public Computer unmount(UsbKey usbKey) throws ComputerException {
        return unmount(null, usbKey);
    }

    public Computer unmount(SdCard sdCard, UsbKey usbKey) throws ComputerException {
        if (usbKey == null) {
            if (this.sdCard != null) {
                return new Computer(this.brand, this.model, this.manufactureDate, this.expirationDate, this.numberOfPreviousOwner, this.working, this.ssdDisk, this.usbKey);
            } else {
                throw new ComputerException("This computer hasn´t sdCard memory");
            }
        } else {
            if (sdCard == null) {
                if (this.usbKey != null) {
                    return new Computer(this.brand, this.model, this.manufactureDate, this.expirationDate, this.numberOfPreviousOwner, this.working, this.ssdDisk, this.sdCard);
                } else {
                    throw new ComputerException("This computer hasn´t usbKey memory");
                }
            } else {
                if (this.usbKey == null || this.sdCard == null) {
                    throw new ComputerException("This computer hasn´t the memory you want to use");
                } else {
                    return new Computer(this.brand, this.model, this.manufactureDate, this.expirationDate, this.numberOfPreviousOwner, this.working, this.ssdDisk);
                }
            }
        }
    }

    public Computer(String brand, String model, String manufactureDate, String expirationDate, int numberOfPreviousOwner, Boolean working, SsdDisk ssdDisk) {
        this(brand, model, manufactureDate, expirationDate, numberOfPreviousOwner, working, ssdDisk, null, null);
    }

    public Computer(String brand, String model, String manufactureDate, String expirationDate, int numberOfPreviousOwner, Boolean working, SsdDisk ssdDisk, SdCard sdCard) {
        this(brand, model, manufactureDate, expirationDate, numberOfPreviousOwner, working, ssdDisk, sdCard, null);
    }

    public Computer(String brand, String model, String manufactureDate, String expirationDate, int numberOfPreviousOwner, Boolean working, SsdDisk ssdDisk, UsbKey usbKey) {
        this(brand, model, manufactureDate, expirationDate, numberOfPreviousOwner, working, ssdDisk, null, usbKey);
    }

    public Computer(String brand, String model, String manufactureDate, String expirationDate, int numberOfPreviousOwner, Boolean working, SsdDisk ssdDisk,
            SdCard sdCard, UsbKey usbKey) {
        this.brand = brand;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.numberOfPreviousOwner = numberOfPreviousOwner;
        this.working = working;
        this.ssdDisk = ssdDisk;
        this.sdCard = sdCard;
        this.usbKey = usbKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Computer)) {
            return false;
        }
        Computer computer = (Computer) o;
        return numberOfPreviousOwner == computer.numberOfPreviousOwner && Objects.equals(brand, computer.brand) && Objects.equals(model, computer.model)
                && Objects.equals(manufactureDate, computer.manufactureDate) && Objects.equals(expirationDate, computer.expirationDate) && Objects.equals(working,
                computer.working);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, manufactureDate, expirationDate, numberOfPreviousOwner, working);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "znacka='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", datumVyroby=" + manufactureDate +
                ", datumExpirace=" + expirationDate +
                ", pocetPredeslychMajitelu=" + numberOfPreviousOwner +
                ", funkcni=" + working +
                '}';
    }


    public void setSdCard(SdCard sdCard) {
        this.sdCard = sdCard;
    }

    public void setUsbKey(UsbKey usbKey) {
        this.usbKey = usbKey;
    }


    public SdCard getSdCard() {
        return sdCard;
    }

    public UsbKey getUsbKey() {
        return usbKey;
    }

    @Override
    public Boolean isWorking() {
        if (this.sdCard == null || this.usbKey == null) {
            if (this.sdCard == null && this.usbKey == null) {
                return working && ssdDisk.isWorking();
            } else {
                if (this.sdCard == null) {
                    return working && ssdDisk.isWorking() && usbKey.isWorking();
                } else {
                    return working && ssdDisk.isWorking() && sdCard.isWorking();
                }
            }
        } else {
            return working && ssdDisk.isWorking() && sdCard.isWorking() && usbKey.isWorking();
        }
    }
}
