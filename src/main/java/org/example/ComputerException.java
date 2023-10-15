package org.example;

public class ComputerException extends Exception{

    public ComputerException(String message) {
        super(message);
    }

    public ComputerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComputerException(Throwable cause) {
        super(cause);
    }
}
