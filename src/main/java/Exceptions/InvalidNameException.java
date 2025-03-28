package Exceptions;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException(){
        super("[INVALID ITEM NAME]");
    }

    public InvalidNameException(String name) {
        super("[INVALID ITEM NAME]: " + name);
    }
}
