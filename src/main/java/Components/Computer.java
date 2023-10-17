package Components;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
Vytvor triedu Components.Computer s nasledovnými atribútmi:
značka
model
dátum výroby
dátum expiracie
počet predošlých majiteľov
stav reprezentujúci funkčnsť (áno vs nie)

 */
public class Computer implements Workable {
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
            int gelper = i.getActualCapacity();
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
            throw new NotEnoughMemoryException(memorySize - getActualCapacity());
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
            if(helper >= memorySize){
                i.useMemory(memorySize);
                return getActualCapacity();
            } else {
                i.useMemory(helper);
            }
            //i.useMemory(helper);
            memorySize -= helper;
        }
        return getActualCapacity();
    }

    public boolean canRemoveMemory(int memorySize) throws NotEnoughMemoryException{
        return (getTotalCapacity() - getActualCapacity()) >= memorySize;
    }

    public int removeMemory(int memorySize) throws NotEnoughMemoryException {
        if(!canRemoveMemory(memorySize)){
            throw new NotEnoughMemoryException(memorySize);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Computer computer = (Computer) o;
        return numPreviousOwners == computer.numPreviousOwners && working == computer.working && brand.equals(computer.brand) && model.equals(computer.model)
                && dateOfManufacture.equals(computer.dateOfManufacture) && dateOfExpiration.equals(computer.dateOfExpiration) && components.equals(computer.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, dateOfManufacture, dateOfExpiration, numPreviousOwners, working, components);
    }

    public float getPercentageUsage() {
        return ( ( (float)getTotalCapacity() - getActualCapacity() )*100)/getTotalCapacity();
    }

    @Override
    public boolean isWorkable() {
        return this.working;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "znacka='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", datum vyroby='" + dateOfManufacture + '\'' +
                ", datum expirace='" + dateOfExpiration + '\'' +
                ", pocet predeslich majitelu=" + numPreviousOwners +
                ", funkcni=" + working +
                ", komponenty=" + components +
                '}';
    }
}
