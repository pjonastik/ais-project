package org.example.product.computer.exception;

/**
 * This should have better name in requirements!
 */
public class SsdDiskUnmountableException extends UnmountMemoryException {
    public SsdDiskUnmountableException(String message) {
        super(message);
    }
}
