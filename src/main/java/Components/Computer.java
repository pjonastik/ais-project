import java.util.ArrayList;
import java.util.List;

/*
Vytvor triedu Computer s nasledovnými atribútmi:
značka
model
dátum výroby
dátum expiracie
počet predošlých majiteľov
stav reprezentujúci funkčnsť (áno vs nie)

 */
public class Computer {
    public String brand;
    public String model;
    public String dateOfManufacture;
    public String dateOfExpiration;
    public int numPreviousOwners;
    public boolean working;

    public List<AbstractComponent> components = new ArrayList<AbstractComponent>();

    public Computer(String brand, String model, String dateOfManufacture, String dateOfExpiration, int numPreviousOwners, boolean working, SsdDisk ssdDisk) {
        this.brand = brand;
        this.model = model;
        this.dateOfManufacture = dateOfManufacture;
        this.dateOfExpiration = dateOfExpiration;
        this.numPreviousOwners = numPreviousOwners;
        this.working = working;
        this.components.add(ssdDisk);
    }

    public void mount(AbstractComponent component) throws SsdDiskUnmoutableException {
        if(component instanceof SsdDisk){
            throw new SsdDiskUnmoutableException();
        }
        else{
            components.add(component);
        }
    }

    public void unmount(AbstractComponent component) throws SsdDiskUnmoutableException{
        if(component instanceof SsdDisk){
            throw new SsdDiskUnmoutableException();
        }
        else{
            components.remove(component);
        }
    }

    public int getTotalCapacity() {
        int totalCapacity = 0;
        for(AbstractComponent i : components) {
            totalCapacity += i.getTotalCapacity();
        }
        return totalCapacity;
    }

    public int getActualCapacity() {
        int actualCapacity = 0;
        for(AbstractComponent i : components) {
            actualCapacity += i.getActualCapacity();
        }
        return actualCapacity;
    }

    public boolean canUseMemory(int memorySize) throws ComponentIllegalStateException, NotEnoughMemoryException{
        try{
            for(AbstractComponent i : components){
                i.canUseMemory(memorySize);
            }
        } catch (ComponentIllegalStateException e){
            throw e;
        }
        if(getActualCapacity() < memorySize) {
            throw new NotEnoughMemoryException();
        }
        return true;
    }

    public int useMemory(int memorySize) throws ComponentIllegalStateException, NotEnoughMemoryException {
        try{
            canUseMemory(memorySize);
        } catch (ComponentIllegalStateException | NotEnoughMemoryException e){
            throw e;
        }
        int helper = 0;
        for(AbstractComponent i : components){
            helper = i.getActualCapacity();
            i.useMemory(helper);
            memorySize -= helper;
        }
        return getActualCapacity();
    }

    public boolean canRemoveMemory(int memorySize) throws NotEnoughMemoryException{
        return (getTotalCapacity() - getActualCapacity()) >= memorySize;
    }

    public int removeMemory(int memorySize) throws NotEnoughMemoryException {
        if(!canRemoveMemory(memorySize)){
            throw new NotEnoughMemoryException();
        }

        int helper = 0;
        for(AbstractComponent i : components) {
            helper = i.usedMemory;
            if(helper <= memorySize){
                i.removeMemory(helper);
            } else {
                i.removeMemory(memorySize);
            }
            memorySize -= helper;
            if(memorySize <= 0){
                return getActualCapacity();
            }
        }

        return getActualCapacity();
    }

    public float getPercentageUsage() {
        return ((float) getActualCapacity()*100)/getTotalCapacity();
    }
}
