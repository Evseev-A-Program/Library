package exceptions;

public class ClientNotFoindException extends Exception{
    public ClientNotFoindException() {
    }

    public ClientNotFoindException(String message) {
        super(message);
    }

    public ClientNotFoindException(String message, Throwable cause) {
        super(message, cause);
    }
}
