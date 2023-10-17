package org.example.product.computer.exception;

import org.example.product.memory.exception.MemoryException;

/**
 * This should have better name in requirements!
 */
public class SsdDiskUnmountableException extends MemoryException {
    public SsdDiskUnmountableException(String message) {
        super(message);
    }
}
