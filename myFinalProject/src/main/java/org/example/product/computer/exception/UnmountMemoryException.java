package org.example.product.computer.exception;

import org.example.product.memory.exception.MemoryException;

public class UnmountMemoryException extends MemoryException {
    public UnmountMemoryException(String message) {
        super(message);
    }
}
