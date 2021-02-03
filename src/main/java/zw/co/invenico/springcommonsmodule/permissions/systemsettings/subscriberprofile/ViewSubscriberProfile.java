package zw.co.invenico.springcommonsmodule.permissions.systemsettings.subscriberprofile;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@PreAuthorize("hasAuthority('VIEW_SHIFT_DIRECTORATE')")
public @interface ViewSubscriberProfile {
}
