package Components;

public class NotEnoughMemoryException extends Exception{

    public NotEnoughMemoryException(int mem){
        super("Not enough memory. Memory to be used: " + mem);
    }

}
