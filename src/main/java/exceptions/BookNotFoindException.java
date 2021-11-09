package exceptions;

public class BookNotFoindException extends Exception{
    public BookNotFoindException() {
    }

    public BookNotFoindException(String message) {
        super(message);
    }

    public BookNotFoindException(String message, Throwable cause) {
        super(message, cause);
    }
}
