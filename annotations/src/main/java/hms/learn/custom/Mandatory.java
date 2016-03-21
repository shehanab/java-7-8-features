package hms.learn.custom;

import java.lang.annotation.*;

/**
 * Created by Shehan on 6/3/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(value = {ElementType.TYPE_PARAMETER, ElementType.TYPE_USE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.TYPE, ElementType.LOCAL_VARIABLE})
public @interface Mandatory {

}
