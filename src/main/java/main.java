import Components.ComponentIllegalStateException;
import Components.Computer;
import Components.NotEnoughMemoryException;
import Components.SdCard;
import Components.SsdDisk;
import Components.SsdDiskUnmoutableException;
import Components.UsbKey;

class main{
    public static void main(String args[]){
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
        UsbKey usbKey2 = new UsbKey("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                2,
                0,
                true);

        SdCard sdCard = new SdCard("Intel",
                "XYX",
                "10.05.2023",
                "10.05.2033",
                0,
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
        try {
            computer.mount(usbKey1);
            computer.mount(usbKey2);
            computer.mount(sdCard);
        } catch (SsdDiskUnmoutableException e){
            System.out.println("You tried to mount unmountable component: e");
            return;
        }
        try{
            computer.useMemory(10);
        } catch (ComponentIllegalStateException e){
            System.out.println("One of the components in illegal state");
        } catch (NotEnoughMemoryException e){
            System.out.println("Not enough memory");
        }

        try{
            computer.removeMemory(5);
        } catch (NotEnoughMemoryException e){
            System.out.println("Not enough used memory to remove");
        }
        System.out.println(computer.getPercentageUsage());

    }
}





