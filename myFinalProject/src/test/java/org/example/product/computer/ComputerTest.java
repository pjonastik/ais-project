package org.example.product.computer;

import org.example.product.memory.Memorable;
import org.example.product.memory.SdCard;
import org.example.product.memory.SsdDisk;
import org.example.product.memory.UsbKey;
import org.example.product.memory.exception.MemoryBrokenException;
import org.example.product.memory.exception.RemoveMemoryException;
import org.example.product.memory.exception.UseMemoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ComputerTest {
    @Test
    void getTotalCapacity() {
        Memorable ssdDisk = new SsdDisk("Samsung", "X1", 500);
        Computer computer = new Computer("Lenovo", "T460", ssdDisk);

        assertThat(computer.getTotalCapacity()).isEqualTo(500);
    }

    @Test
    void mountMemoryDevices() {
        Memorable ssdDisk = new SsdDisk("Samsung", "X1", 500);
        Computer computer = new Computer("Lenovo", "T460", ssdDisk);

        Memorable usb = new UsbKey("Keystone", "X2", 40);
        computer.mount(usb);
        assertThat(computer.getTotalCapacity()).isEqualTo(540);

        Memorable sdCard = new SdCard("Keystone", "N2", 60);
        computer.mount(sdCard);
        assertThat(computer.getTotalCapacity()).isEqualTo(600);
    }

    @Test
    void unmountMemoryDevices() {
        Memorable ssdDisk = new SsdDisk("Samsung", "X1", 500);
        Computer computer = new Computer("Lenovo", "T460", ssdDisk);

        Memorable usb = new UsbKey("Keystone", "X2", 40);
        computer.mount(usb);

        Memorable sdCard = new SdCard("Keystone", "N2", 60);
        computer.mount(sdCard);


        computer.unmount(sdCard);
        assertThat(computer.getTotalCapacity()).isEqualTo(540);

        computer.unmount(usb);
        assertThat(computer.getTotalCapacity()).isEqualTo(500);
    }

    @Test
    void unmountUnsupportedMemoryDevices() {
        Assertions.assertThrows(UnmountMemoryException.class, () -> {
            Memorable ssdDisk = new SsdDisk("Samsung", "X1", 500);
            Computer computer = new Computer("Lenovo", "T460", ssdDisk);

            computer.unmount(ssdDisk);
        });
    }

    @Test
    void getActualCapacity() {
        Memorable ssdDisk = new SsdDisk("Samsung", "X1", 500);
        ssdDisk.useMemory(50);

        Computer computer = new Computer("Lenovo", "T460", ssdDisk);

        assertThat(computer.getActualCapacity()).isEqualTo(50);

        Memorable usb = new UsbKey("Keystone", "X2", 50);
        usb.useMemory(10);
        computer.mount(usb);

        Memorable sdCard = new SdCard("Keystone", "N2", 50);
        sdCard.useMemory(10);
        computer.mount(sdCard);

        assertThat(computer.getActualCapacity()).isEqualTo(70);
    }

    @Test
    void getPercentageUsage() {
        Memorable ssdDisk = new SsdDisk("Samsung", "X1", 100);
        ssdDisk.useMemory(50);

        Computer computer = new Computer("Lenovo", "T460", ssdDisk);
        assertThat(computer.getPercentageUsage()).isEqualTo(50f);

        computer.useMemory(50);
        assertThat(computer.getPercentageUsage()).isEqualTo(100f);


        Memorable usb = new UsbKey("Keystone", "X2", 50);
        computer.mount(usb);

        Memorable sdCard = new SdCard("Keystone", "N2", 50);
        computer.mount(sdCard);

        assertThat(computer.getPercentageUsage()).isEqualTo(50f);
    }

    @Test
    void canUseMemory() {
        Memorable ssdDisk = new SsdDisk("Samsung", "X1", 500);
        Computer computer = new Computer("Lenovo", "T460", ssdDisk);


        Memorable usb = new UsbKey("Keystone", "X2", 50);
        Memorable sdCard = new SdCard("Keystone", "N2", 50);
        computer.mount(usb);
        computer.mount(sdCard);

        assertThat(computer.canUseMemory(600)).isTrue();
        assertThat(computer.canUseMemory(601)).isFalse();
    }

    @Test
    void useMemory() {
        Memorable ssdDisk = new SsdDisk("Samsung", "X1", 500);
        Computer computer = new Computer("Lenovo", "T460", ssdDisk);

        Memorable usb = new UsbKey("Keystone", "X2", 50);
        Memorable sdCard = new SdCard("Keystone", "N2", 50);
        computer.mount(usb);
        computer.mount(sdCard);

        computer.useMemory(50);
        computer.useMemory(50);
        computer.useMemory(50);
        computer.useMemory(50);

        assertThat(computer.getActualCapacity()).isEqualTo(200);
    }

    @Test
    void useMemory_NotEnoughMemorySpace() {
        Assertions.assertThrows(UseMemoryException.class, () -> {
            Memorable ssdDisk = new SsdDisk("Samsung", "X1", 500);
            Computer computer = new Computer("Lenovo", "T460", ssdDisk);

            computer.useMemory(501);
        });
    }

    @Test
    void useMemory_BrokenMemory() {
        Assertions.assertThrows(MemoryBrokenException.class, () -> {
            SsdDisk ssdDisk = new SsdDisk("Samsung", "X1", 500);
            ssdDisk.setWorking(false);
            Computer computer = new Computer("Lenovo", "T460", ssdDisk);

            computer.useMemory(1);
        });
    }

    @Test
    void removeMemory() {
        Memorable ssdDisk = new SsdDisk("Samsung", "X1", 500);
        ssdDisk.useMemory(100);
        Computer computer = new Computer("Lenovo", "T460", ssdDisk);

        computer.removeMemory(30);
        computer.removeMemory(30);
        computer.removeMemory(30);

        assertThat(computer.getActualCapacity()).isEqualTo(10);
    }

    @Test
    void removeMemory_BrokenMemory() {
        Assertions.assertThrows(MemoryBrokenException.class, () -> {
            SsdDisk ssdDisk = new SsdDisk("Samsung", "X1", 500);
            ssdDisk.useMemory(100);
            ssdDisk.setWorking(false);
            Computer computer = new Computer("Lenovo", "T460", ssdDisk);

            computer.removeMemory(30);
        });
    }

    @Test
    void removeMemory_NotEnoughActualUsedMemory() {
        Assertions.assertThrows(RemoveMemoryException.class, () -> {
            SsdDisk ssdDisk = new SsdDisk("Samsung", "X1", 500);
            ssdDisk.useMemory(1);
            ssdDisk.setWorking(false);
            Computer computer = new Computer("Lenovo", "T460", ssdDisk);

            computer.removeMemory(2);
        });
    }

    @Test
    void canRemoveMemory() {
        Memorable ssdDisk = new SsdDisk("Samsung", "X1", 500);
        Computer computer = new Computer("Lenovo", "T460", ssdDisk);

        assertThat(computer.canRemoveMemory(600)).isFalse();

        computer.useMemory(1);
        assertThat(computer.canRemoveMemory(1)).isTrue();
    }
}