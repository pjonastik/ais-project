package org.example.Memory;

import org.example.ComputerException;
import org.example.Workable;

public abstract class Memory implements Workable {

    public abstract Boolean isWorking() throws ComputerException;

}
