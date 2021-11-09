package exceptions;

public class AccountingRecordNotFoundException extends Exception{
    public AccountingRecordNotFoundException() {
    }

    public AccountingRecordNotFoundException(String message) {
        super(message);
    }

    public AccountingRecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
