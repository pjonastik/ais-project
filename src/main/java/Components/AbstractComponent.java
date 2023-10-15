abstract class AbstractComponent {
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
        if(!this.working){
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
            throw new NotEnoughMemoryException();
        } else {
            usedMemory -= size;
        }
        return getActualCapacity();
    }

    public float getPercentageUsage() {
        return ((float) usedMemory*100)/getTotalCapacity();
    }
}
