package zw.co.invenico.springcommonsmodule.exception;

public class AccountDoesNotExistException extends RuntimeException {

    public AccountDoesNotExistException(final String s) {
        super(s);
    }
}
