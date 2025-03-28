package Exceptions;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException() {
        super("[INVALID ITEM QUANTITY]");
    }

    public InvalidQuantityException(int quantity) {
        super("[INVALID ITEM QUANTITY]: " + quantity);
    }
}
