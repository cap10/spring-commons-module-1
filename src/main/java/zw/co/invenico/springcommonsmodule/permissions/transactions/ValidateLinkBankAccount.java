package zw.co.invenico.springcommonsmodule.permissions.transactions;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@PreAuthorize("hasAuthority('VALIDATE_LINK_BANK_ACCOUNT')")
public @interface ValidateLinkBankAccount {
}
