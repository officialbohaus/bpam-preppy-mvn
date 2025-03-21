package Exceptions;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(){
        super("[INVALID REQUEST]");
    }

    public InvalidRequestException(String message) {
        super("[INVALID REQUEST]: " + message);
    }
}