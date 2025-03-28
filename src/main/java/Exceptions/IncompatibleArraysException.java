package Exceptions;

public class IncompatibleArraysException extends RuntimeException {
    public IncompatibleArraysException() {
        this("Incompatible Array Sizes");
    }

    public IncompatibleArraysException(String message) {
        super(message);
    }
}