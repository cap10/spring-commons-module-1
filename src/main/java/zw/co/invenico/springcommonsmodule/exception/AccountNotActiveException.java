package zw.co.invenico.springcommonsmodule.exception;

public class AccountNotActiveException extends RuntimeException {

    public AccountNotActiveException(final String s) {
        super(s);
    }
}