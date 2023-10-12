package org.example.exceptions;

public class SsdDiskUnmoutableException extends RuntimeException {
    public SsdDiskUnmoutableException(String message) {
        super(message);
    }
}