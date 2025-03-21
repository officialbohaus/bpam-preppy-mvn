package Exceptions;

public class InvalidIIDException extends RuntimeException {
    public InvalidIIDException(){
        super("[INVALID ITEM IID]");
    }

    public InvalidIIDException(String IID) {
        super("[INVALID ITEM IID]: " + IID);
    }
}
