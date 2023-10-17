package Components;

import java.util.Objects;

abstract class AbstractComponent implements Workable{
    public String brand;
    public String model;
    public String dateOfManufacture;
    public String dateOfExpiration;
    public int memorySize;
    public int usedMemory;
    public boolean working;

    public AbstractComponent(String brand, String model, String dateOfManufacture, String dateOfExpiration, int memorySize, int usedMemory, boolean working) {
        this.brand = brand;
        this.model = model;
        this.dateOfManufacture = dateOfManufacture;
        this.dateOfExpiration = dateOfExpiration;
        this.memorySize = memorySize;
        this.usedMemory = usedMemory;
        this.working = working;
    }

    public int getTotalCapacity() {
        return memorySize;
    }

    public int getActualCapacity() {
        return memorySize - usedMemory;
    }

    public boolean canUseMemory(int memorySize) throws ComponentIllegalStateException {
        if(!this.isWorking()){
            throw new ComponentIllegalStateException();
        }
        return getActualCapacity() >= memorySize;
    }

    public int useMemory(int memorySize) throws ComponentIllegalStateException, NotEnoughMemoryException {
        try{
            if(canUseMemory(memorySize)) {
                usedMemory += memorySize;
                return getActualCapacity();
            }
        } catch (ComponentIllegalStateException e) {
            throw e;
        }
        return -1;
    }

    public boolean canRemoveMemory(int size) {
        return usedMemory >= size;
    }

    public int removeMemory(int size) throws NotEnoughMemoryException {
        if(!canRemoveMemory(size)){
            throw new NotEnoughMemoryException(size);
        } else {
            usedMemory -= size;
        }
        return getActualCapacity();
    }

    public float getPercentageUsage() {
        return ((float) usedMemory*100)/getTotalCapacity();
    }

    public boolean isWorking(){
        return working;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractComponent that = (AbstractComponent) o;
        return memorySize == that.memorySize && usedMemory == that.usedMemory && working == that.working && Objects.equals(brand, that.brand) && Objects.equals(
                model, that.model) && Objects.equals(dateOfManufacture, that.dateOfManufacture) && Objects.equals(dateOfExpiration, that.dateOfExpiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, dateOfManufacture, dateOfExpiration, memorySize, usedMemory, working);
    }

    @Override
    public String toString() {
        return "AbstractComponent{" +
                " Znacka ='" + brand + '\'' +
                ", Model ='" + model + '\'' +
                ", Datum vyroby ='" + dateOfManufacture + '\'' +
                ", datum expirace='" + dateOfExpiration + '\'' +
                ", pamet =" + memorySize +
                ", pouzita pamet=" + usedMemory +
                ", funguje =" + working +
                '}';
    }
}
